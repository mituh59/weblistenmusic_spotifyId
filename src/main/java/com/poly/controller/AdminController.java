package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.entity.Users;
import com.poly.service.RolesService;
import com.poly.service.UsersService;



@Controller
public class AdminController {
    private final UsersService userService;
    private final RolesService roleService;

    @Autowired
    public AdminController(UsersService userService, RolesService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    
	@GetMapping("/admin/user")
	public String showAddUserForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
        // Lấy thông tin người dùng từ UserService
        Users user = userService.findById(username);
        model.addAttribute("userDetail", user);
        
		model.addAttribute("user", new Users() );
		model.addAttribute("list", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
		return "/admin/nguoidung/index";
	}

	@PostMapping("/admin/user")
	public String addUser(@ModelAttribute Users user, Model model) {	    
		 userService.create(user);
		return "redirect:/admin/user";
	}
	
	@GetMapping("/admin/user/edit/{username}")
	public String showEditUserForm(@PathVariable String username, Model model) {
		model.addAttribute("user", userService.findById(username));
	    model.addAttribute("roles", roleService.findAll());
		return "/admin/nguoidung/edit-user";
	}
	
	@PostMapping("/admin/user/edit/{username}")
	public String updateUser(@PathVariable String username, @ModelAttribute Users user) {
		userService.update(user);
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/user/delete/{username}")
	public String deleteUser(@PathVariable String username, Model model) {
		
		try {
            userService.deleteById(username);
            model.addAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error deleting user.");
        }
		return "redirect:/admin/user";
	}
}
