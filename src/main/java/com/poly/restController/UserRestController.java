package com.poly.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.MailInfor;
import com.poly.entity.Users;
import com.poly.service.MailerService;
import com.poly.service.UsersService;

@RestController
public class UserRestController {

	@Autowired
	private UsersService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MailerService mailerService;

	@GetMapping("/api/user/profile")
	public Users getUserProfile() {
		// Lấy thông tin người dùng từ SecurityContext
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Kiểm tra nếu người dùng đã xác thực
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();

			// Lấy thông tin người dùng từ UserService
			Users user = userService.findById(username);

			// Nếu tìm thấy người dùng trong cơ sở dữ liệu, trả về thông tin người dùng
			if (user != null) {
				return user;
			}
		}

		// Nếu không có người dùng đăng nhập hoặc không tìm thấy người dùng, trả về một
		// đối tượng Users rỗng
		return new Users();
	}

	@GetMapping("/api/user")
	public List<Users> findAll() {
		List<Users> item = userService.findAll();
		return item;
	}
	
	@GetMapping("/api/user/{id}")
	public Users findById(@PathVariable("id") String id) {
		Users item = userService.findById(id);
		return item;
	}

	@PostMapping("/api/user")
	public Users create(@RequestBody Users item) {
		return userService.create(item);
	}
	
	@PostMapping("/api/user/sendmail/{otp}")
	public void sendmail(@RequestBody Users item, @PathVariable("otp") String otp) {
		try {
			Users u = userService.findById(item.getUsername());
			MailInfor mailInfor = new MailInfor(u.getEmail(), "Mã OTP", "Mã OTP xác thực của bạn: " + otp);
			mailerService.send(mailInfor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/api/user/dangky/{otp}")
	public void sendmail(@RequestBody String item, @PathVariable("otp") String otp) {
		try {
			MailInfor mailInfor = new MailInfor(item, "Mã OTP", "Mã OTP xác thực của bạn: " + otp);
			mailerService.send(mailInfor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@PutMapping("/api/user/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Users item) {
		userService.update(item);
	}

	@PutMapping("/api/user/changepassword/{id}")
	public boolean changePW(@PathVariable("id") String id, @RequestBody Users item) {
		Users existingUser = userService.findById(id);

	    // Kiểm tra xem mật khẩu cũ người dùng nhập có đúng không
	    if (passwordEncoder.matches(item.getPassword(), existingUser.getPassword())) {
	        return true;
	    } 
	    
		return false;
	}

	@DeleteMapping("/api/user/{id}")
	public void delete(@PathVariable("id") String id) {
		userService.deleteById(id);
	}

}
