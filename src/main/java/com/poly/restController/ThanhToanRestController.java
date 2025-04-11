package com.poly.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.poly.entity.ThanhToan;
import com.poly.service.ThanhToanService;

@RestController
@CrossOrigin("*") 
public class ThanhToanRestController {

	@Autowired
	ThanhToanService thanhToanService;
	
    @GetMapping("/api/thanhtoan")
	public List<ThanhToan> findAll(){
		List<ThanhToan> list = thanhToanService.findAll();
		return list;
	}
	@GetMapping("/api/thanhtoan/{id}")
	public ThanhToan findById(@PathVariable("id") Integer id) {
		ThanhToan item = thanhToanService.findById(id);
		return item;
	}


}