package com.poly.restController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.DanhGiaDAO;
import com.poly.entity.DanhGia;
import com.poly.service.DanhGiaService;

@RestController
@RequestMapping("/api/danhgia")

@CrossOrigin("*") 
public class DanhGiaRestController {

	 @Autowired
	    private DanhGiaService danhGiaService;

	 	private DanhGiaDAO dao;
	    // ✅ Thêm đánh giá mới
	 	
	 	@GetMapping
	 	public List<DanhGia> getAllDanhGia() {
	 	    return danhGiaService.findAll();
	 	}
	 	
	    @PostMapping("/add")
	    public ResponseEntity<DanhGia> addDanhGia(@RequestBody DanhGia danhGia) {
	        return ResponseEntity.ok(danhGiaService.save(danhGia));
	    }

	    // ✅ Lấy danh sách đánh giá của một bài hát
	    @GetMapping("/{baiHatId}")
	    public ResponseEntity<List<DanhGia>> getDanhGiaByBaiHat(@PathVariable Integer baiHatId) {
	        return ResponseEntity.ok(danhGiaService.getDanhGiaByBaiHat(baiHatId));
	    }

	    // ✅ Xóa đánh giá theo ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDanhGia(@PathVariable Integer id) {
	        danhGiaService.deleteById(id);
	        return ResponseEntity.ok().build();
	    }

	    // ✅ Lấy điểm trung bình của bài hát
	    @GetMapping("/diem/{baiHatId}")
	    public ResponseEntity<Double> getDiemTrungBinh(@PathVariable Integer baiHatId) {
	        return ResponseEntity.ok(danhGiaService.getDiemTrungBinh(baiHatId));
	    }
	    @PostMapping("/danhgia/add")
	    public ResponseEntity<?> addRating(@RequestBody DanhGia danhGia) {
	        String username = danhGia.getUser().getUsername();
	        Integer baiHatId = danhGia.getBaiHat().getBaiHatId();

	        // Kiểm tra xem user đã đánh giá bài hát này chưa
	        boolean exists = dao.existsByUser_UsernameAndBaiHat_BaiHatId(username, baiHatId);
	        if (exists) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn chỉ có thể đánh giá một lần!");
	        }

	        danhGia.setNgayTao(new Date());
	        dao.save(danhGia);
	        return ResponseEntity.ok(danhGia);
	    }
	    
}