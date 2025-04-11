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
import com.poly.service.TheLoaiService;

@RestController
public class TheLoaiRestController {

	@Autowired
	TheLoaiService theloaiService;
	
	@GetMapping("/api/theloai")
	public List<TheLoai> findAll() {
		List<TheLoai> list = theloaiService.findAll();
		return list;
	}
	
	@GetMapping("/api/theloai/{id}")
	public TheLoai findById(@PathVariable("id") Integer id) {
		TheLoai item = theloaiService.findById(id);
		return item;
	}

	@PostMapping("/api/theloai")
	public TheLoai create(@RequestBody TheLoai item) {
		return theloaiService.create(item);
	}

	@PutMapping("/api/theloai/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody TheLoai item) {
		theloaiService.update(item);
	}

	@DeleteMapping("/api/theloai/{id}")
	public void delete(@PathVariable("id") Integer id) {
		theloaiService.deleteById(id);
	}
}
