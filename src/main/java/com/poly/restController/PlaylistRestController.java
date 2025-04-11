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

import com.poly.entity.Playlist;
import com.poly.service.PlayListService;

@RestController
public class PlaylistRestController {

	@Autowired
	PlayListService playlistService;
	
	@GetMapping("/api/playlist")
	public List<Playlist> findAll(){
		return playlistService.findAll();
	}
	
	@GetMapping("/api/playlist/{id}")
	public Playlist findById(@PathVariable Integer id){
		return playlistService.findById(id);
	}
	
	@PostMapping("/api/playlist")
	public Playlist create(@RequestBody Playlist item) {
		return playlistService.create(item);
	}

	@PutMapping("/api/playlist/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody Playlist item) {
		playlistService.update(item);
	}

	@DeleteMapping("/api/playlist/{id}")
	public void delete(@PathVariable("id") Integer id) {
		playlistService.deleteById(id);
	}
}
