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

import com.poly.entity.GoiPremium;
import com.poly.service.impl.GoiPremiumServiceImpl;

@RestController
public class GoiPremiumRestController {
	
	@Autowired
	GoiPremiumServiceImpl goiPremiumServiceImpl;
	
	@GetMapping("/api/goipremium")
	public List<GoiPremium> findAll() {
		List<GoiPremium> list = goiPremiumServiceImpl.findAll();
		return list;
	}
	
	@GetMapping("/api/goipremium/{id}")
	public GoiPremium findById(@PathVariable("id") Integer id) {
		GoiPremium item = goiPremiumServiceImpl.findById(id);
		return item;
	}

	@PostMapping("/api/goipremium")
	public GoiPremium create(@RequestBody GoiPremium item) {
		return goiPremiumServiceImpl.create(item);
	}

	@PutMapping("/api/goipremium/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody GoiPremium item) {
		goiPremiumServiceImpl.update(item);
	}

	@DeleteMapping("/api/goipremium/{id}")
	public void delete(@PathVariable("id") Integer id) {
		goiPremiumServiceImpl.deleteById(id);
	}
}
