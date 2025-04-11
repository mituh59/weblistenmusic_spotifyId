package com.poly.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.BinhLuan;
import com.poly.service.BinhLuanService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class BinhLuanRestController {
	@Autowired
    private BinhLuanService binhLuanService;

	@GetMapping("/")
	public List<BinhLuan> findAll(){
		return binhLuanService.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		binhLuanService.deleteById(id);
	}
	
    @GetMapping("/{baiHatId}")
    public ResponseEntity<List<BinhLuan>> getCommentsByBaiHat(@PathVariable Integer baiHatId) {
        return ResponseEntity.ok(binhLuanService.getCommentsByBaiHat(baiHatId));
    }
    
    @PostMapping("/add")
    public ResponseEntity<BinhLuan> addComment(@RequestBody BinhLuan binhLuan) {
        return ResponseEntity.ok(binhLuanService.addComment(binhLuan));
    }

}
