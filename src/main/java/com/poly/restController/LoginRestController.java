package com.poly.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dto.request.AuthenticationRequest;
import com.poly.dto.request.IntrospectRequest;
import com.poly.dto.response.AuthenticationResponse;
import com.poly.dto.response.IntrospectResponse;
import com.poly.entity.Users;
import com.poly.service.impl.AuthenticationServiceImpl;
import com.poly.service.impl.UsersServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginRestController {
	 @Autowired
	    AuthenticationServiceImpl authServiceImpl;
	 @Autowired
	    private UsersServiceImpl userService;

	    @PostMapping("/token")
	    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) throws Exception {
	        // Gọi phương thức authenticate để kiểm tra mật khẩu
	        var result = authServiceImpl.authenticate(request);

	        // Tạo phản hồi với kết quả đăng nhập
	        return AuthenticationResponse.builder()
	        		.authenticated(result.isAuthenticated())
	                .token(result.getToken()) 
	                .build();
	    }
	    
	    @PostMapping("/introspect")
	    public IntrospectResponse introspect(@RequestBody IntrospectRequest request) throws Exception {
	        var result =  authServiceImpl.introspect(request);

	        return IntrospectResponse.builder()	
	        		.valid(result.isValid())
	                .build();
	    }
	

	    // Phương thức GET lấy tất cả tài khoản và mật khẩu
//	    @PreAuthorize("hasRole('Admin')")
	    @GetMapping("/users/accounts")
	    public List<Users> getAllUserAccounts() {
	        return userService.findAll();
	    }
	    
	    // Phương thức GET lấy tất cả tài khoản và mật khẩu
//	    @PreAuthorize("hasRole('Admin')")
	    @RequestMapping("/users/accounts-details/{id}")
	    public Users getUserAccount(Model model, @PathVariable("id") String id) {
	    	var authenticaton = SecurityContextHolder.getContext().getAuthentication();
	    	System.out.println("Username: " + authenticaton.getName());
	    	authenticaton.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
	    	
	        return userService.findById(id);
	    }
	
}
