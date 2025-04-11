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

import com.poly.entity.ChiTietPlaylist;
import com.poly.service.ChiTietPlaylistService;
import com.poly.service.PlayListService;

@RestController
public class ChiTietPlaylistRestController {

	@Autowired
	ChiTietPlaylistService ctplaylistService;
	
	@Autowired
	PlayListService playlistService;
	
	@GetMapping("/api/chitietplaylist")
	public List<ChiTietPlaylist> findAll(){
		return ctplaylistService.findAll();
	}
	
	@GetMapping("/api/chitietplaylist/{id}")
	public ChiTietPlaylist findById(@PathVariable Integer id){
		return ctplaylistService.findById(id);
	}
	
	@GetMapping("/api/chitietplaylist/playlist/{id}")
	public List<ChiTietPlaylist> findByPlaylistId(@PathVariable Integer id){
		return ctplaylistService.findByPlaylist(playlistService.findById(id));
	}
	
	@PostMapping("/api/chitietplaylist")
	public ChiTietPlaylist create(@RequestBody ChiTietPlaylist item) {
		return ctplaylistService.create(item);
	}
	
	@PostMapping("/api/chitietplaylist/{playlistId}")
	public List<ChiTietPlaylist> creates(@RequestBody List<ChiTietPlaylist> items, @PathVariable Integer playlistId) {
		for (ChiTietPlaylist item : items) {
			item.setId(null);
			item.setPlaylist(playlistService.findById(playlistId));
            ctplaylistService.create(item);
        }
		return items;
	}

	@PutMapping("/api/chitietplaylist/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody ChiTietPlaylist item) {
		ctplaylistService.update(item);
	}

	@DeleteMapping("/api/chitietplaylist/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ctplaylistService.deleteById(id);
	}
}
