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

import com.poly.entity.Album;
import com.poly.service.AlbumService;
@RestController
public class AlbumRestController {
	@Autowired
	AlbumService albumService;
	
	@GetMapping("/api/album")
	public List<Album> findAll(){
		List<Album> list = albumService.findAll();
		return list;
	}
	
	@GetMapping("/api/album/getrandom")
	public List<Album> findRandom(Integer r){
		List<Album> list = albumService.getRandomAlbum(r);
		return list;
	}
	
	@GetMapping("/api/album/{id}")
	public Album findById(@PathVariable("id") Integer id) {
		Album item = albumService.findById(id);
		return item;
	}

	@PostMapping("/api/album")
	public Album create(@RequestBody Album item) {
		return albumService.create(item);
	}

	@PutMapping("/api/album/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody Album item) {
		albumService.update(item);
	}

	@DeleteMapping("/api/album/{id}")
	public void delete(@PathVariable("id") Integer id) {
		albumService.deleteById(id);
	}
}
