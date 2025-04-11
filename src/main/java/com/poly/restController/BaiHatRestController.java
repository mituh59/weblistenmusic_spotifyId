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

import com.poly.entity.BaiHat;
import com.poly.service.BaiHatService;

@RestController
public class BaiHatRestController {

	@Autowired
	BaiHatService baihatService;
	
	@GetMapping("/api/baihat")
	public List<BaiHat> findAll(){
		List<BaiHat> list = baihatService.findAll();
		return list;
	}
	
	@GetMapping("/api/baihat/gettop10/{index}")
	public List<BaiHat> getTop10(@PathVariable("index") Integer r){
		List<BaiHat> list = baihatService.getTop10(r);
		return list;
	}
	
	@GetMapping("/api/baihat/search/{keyword}")
	public List<BaiHat> search(@PathVariable("keyword") String keyword){
		List<BaiHat> list = baihatService.searchBaiHat("%"+keyword+"%");
		return list;
	}
	
	@GetMapping("/api/baihat/{id}")
	public BaiHat findById(@PathVariable("id") Integer id) {
		BaiHat item = baihatService.findById(id);
		return item;
	}

	@PostMapping("/api/baihat")
	public BaiHat create(@RequestBody BaiHat item) {
		return baihatService.create(item);
	}

	@PutMapping("/api/baihat/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody BaiHat item) {
		baihatService.update(item);
	}

	@DeleteMapping("/api/baihat/{id}")
	public void delete(@PathVariable("id") Integer id) {
		baihatService.deleteById(id);
	}
}
