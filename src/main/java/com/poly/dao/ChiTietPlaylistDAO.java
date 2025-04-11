package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.ChiTietPlaylist;
import com.poly.entity.Playlist;

public interface ChiTietPlaylistDAO extends JpaRepository<ChiTietPlaylist, Integer>{
	List<ChiTietPlaylist> findByPlaylist(Playlist playlist);
}
