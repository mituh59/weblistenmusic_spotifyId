package com.poly.configpayment;

import com.poly.dao.GoiPremiumDAO;
import com.poly.dao.ThanhToanDAO;
import com.poly.dao.UserPremiumDAO;
import com.poly.dao.UsersDAO;
import com.poly.entity.GoiPremium;
import com.poly.entity.ThanhToan;
import com.poly.entity.UserPremium;
import com.poly.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {

    @Autowired
    private ThanhToanDAO thanhToanDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private GoiPremiumDAO goiPremiumDAO;

    @Autowired
    private UserPremiumDAO userPremiumDAO;


    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createPayment(@RequestBody PaymentRequest request) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = request.getAmount() * 100; // VNPAY yêu cầu nhân 100
        String bankCode = "NCB";

        String vnp_TxnRef = ConfigPayment.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1"; // Lấy IP thực tế nếu cần
        String vnp_TmnCode = ConfigPayment.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng: " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", ConfigPayment.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        // Thời gian tạo và hết hạn giao dịch
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Tạo chuỗi ký hash và URL query
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (Iterator<String> itr = fieldNames.iterator(); itr.hasNext(); ) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII)).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        // Tạo secure hash
        String vnp_SecureHash = ConfigPayment.hmacSHA512(ConfigPayment.secretKey, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        // URL thanh toán
        String paymentUrl = ConfigPayment.vnp_PayUrl + "?" + query.toString();

        // Trả về response JSON
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/save")
    public ResponseEntity<?> savePayment(
            @RequestParam("vnp_Amount") Double amount,
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("email") String email) {

        try {
            // Kiểm tra email hợp lệ
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Email không hợp lệ");
            }

            // Lấy user từ database
            Users user = usersDAO.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Lấy gói premium theo giá (chuyển amount từ VNPay về đúng đơn vị tiền tệ)
            GoiPremium goiPremium = goiPremiumDAO.findByGia(amount / 100)
                    .orElseThrow(() -> new RuntimeException("Gói premium không tồn tại"));

            // Tạo bản ghi thanh toán (ban đầu chưa thanh toán)
            ThanhToan thanhToan = ThanhToan.builder()
                    .user(user)
                    .goiPremium(goiPremium)
                    .soTien(amount / 100)
                    .phuongThuc("VNPAY")
                    .ngayThanhToan(new Date())
                    .trangThai(false) // Mặc định chưa thanh toán
                    .build();
            thanhToanDAO.save(thanhToan);

            // Nếu thanh toán thành công (responseCode = "00"), cập nhật trạng thái
            if ("00".equals(responseCode)) {
                thanhToan.setTrangThai(true);
                thanhToanDAO.save(thanhToan);

                // Xử lý UserPremium
                Optional<UserPremium> userPremiumOpt = userPremiumDAO.findFirstByUserOrderByIdDesc(user);
                Date today = new Date();
                Calendar calendar = Calendar.getInstance();

                if (userPremiumOpt.isPresent()) {
                    UserPremium userPremium = userPremiumOpt.get();
                    Date ngayHetHan = userPremium.getNgayHetHan();

                    if (ngayHetHan.before(today)) {
                        userPremium.setNgayBatDau(today);
                        calendar.setTime(today);
                        calendar.add(Calendar.DAY_OF_MONTH, goiPremium.getThoiHan());
                        userPremium.setNgayHetHan(calendar.getTime());
                    } else {
                        calendar.setTime(ngayHetHan);
                        calendar.add(Calendar.DAY_OF_MONTH, goiPremium.getThoiHan());
                        userPremium.setNgayHetHan(calendar.getTime());
                    }

                    userPremium.setGoiPremium(goiPremium);
                    userPremiumDAO.save(userPremium);
                } else {
                    calendar.setTime(today);
                    calendar.add(Calendar.DAY_OF_MONTH, goiPremium.getThoiHan());

                    UserPremium newUserPremium = UserPremium.builder()
                            .user(user)
                            .goiPremium(goiPremium)
                            .ngayBatDau(today)
                            .ngayHetHan(calendar.getTime())
                            .trangThai(true)
                            .build();
                    userPremiumDAO.save(newUserPremium);
                }
            }

            return ResponseEntity.ok("Thanh toán đã được lưu");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi khi xử lý thanh toán: " + e.getMessage());
        }
    }

}
