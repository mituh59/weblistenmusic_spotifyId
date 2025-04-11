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

import com.poly.entity.NgheSi;
import com.poly.service.NgheSiService;

@RestController
public class NgheSiRestController {
	
	@Autowired
	NgheSiService nghesiService;
	
	@GetMapping("/api/nghesi")
	public List<NgheSi> findAll(){
		List<NgheSi> list = nghesiService.findAll();
		return list;
	}
	
	@GetMapping("/api/nghesi/{id}")
	public NgheSi findById(@PathVariable("id") String id) {
		NgheSi item = nghesiService.findById(id);
		return item;
	}

	@PostMapping("/api/nghesi")
	public NgheSi create(@RequestBody NgheSi item) {
		return nghesiService.create(item);
	}

	@PutMapping("/api/nghesi/{id}")
	public void update(@PathVariable("id") String id, @RequestBody NgheSi item) {
		nghesiService.update(item);
	}

	@DeleteMapping("/api/nghesi/{id}")
	public void delete(@PathVariable("id") String id) {
		nghesiService.deleteById(id);
	}
}
