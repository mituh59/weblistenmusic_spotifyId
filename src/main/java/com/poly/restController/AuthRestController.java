package com.poly.restController;

import com.poly.dao.UsersDAO;
import com.poly.entity.Users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthRestController {
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
	    }
	    return ResponseEntity.ok(user.getAttributes());
	}

}
