package com.poly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dto.request.AuthenticationRequest;
import com.poly.dto.response.AuthenticationResponse;
import com.poly.service.impl.AuthenticationServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;

@Controller
public class LoginController {

	@Autowired
	AuthenticationServiceImpl authServiceImpl;

//     Hiển thị trang đăng nhập
	@GetMapping("/login")
	public String loginPage() {
		return "/login"; // Trả về trang login.html trong thư mục admin
	}

	// Xử lý đăng nhập
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
	        Model model) {
	    try {
	        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
	        AuthenticationResponse response = authServiceImpl.authenticate(authenticationRequest);
	        return "redirect:/admin/user";
	    } catch (Exception e) {
	        model.addAttribute("error", "Invalid username or password");
	        return "/login"; // Trả về trang login với thông báo lỗi
	    }
	}




	private void clearCookies(HttpServletRequest request, HttpServletResponse response) {
	    // Tìm và xóa cookie "JSESSIONID" nếu có
	    Cookie sessionCookie = new Cookie("JSESSIONID", null);
	    sessionCookie.setMaxAge(0);  // Thiết lập thời gian sống của cookie là 0 để xóa nó
	    sessionCookie.setPath("/");  // Đảm bảo rằng cookie này bị xóa ở tất cả các đường dẫn
	    response.addCookie(sessionCookie);

	    // Xóa cookies liên quan đến token
	    Cookie accessTokenCookie = new Cookie("access_token", null);
	    accessTokenCookie.setMaxAge(0);
	    accessTokenCookie.setPath("/");
	    response.addCookie(accessTokenCookie);

	    Cookie refreshTokenCookie = new Cookie("refresh_token", null);
	    refreshTokenCookie.setMaxAge(0);
	    refreshTokenCookie.setPath("/");
	    response.addCookie(refreshTokenCookie);
	}

}
