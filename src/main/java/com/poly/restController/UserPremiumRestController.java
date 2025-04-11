package com.poly.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.TheLoai;
import com.poly.entity.UserPremium;
import com.poly.service.UserPremiumService;

@RestController
public class UserPremiumRestController {

	@Autowired
	UserPremiumService userpremiumService;
	
	@GetMapping("/api/userpremium")
	public List<UserPremium> findAll(){
		return userpremiumService.findAll();
	}
	
	@PostMapping("/api/userpremium")
	public UserPremium create(@RequestBody UserPremium item) {
		return userpremiumService.create(item);
	}

	@PutMapping("/api/userpremium/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody UserPremium item) {
		userpremiumService.update(item);
	}

	@DeleteMapping("/api/userpremium/{id}")
	public void delete(@PathVariable("id") Integer id) {
		userpremiumService.deleteById(id);
	}
}
