package com.poly.service;

import java.util.List;

import com.poly.entity.ChiTietPlaylist;
import com.poly.entity.Playlist;

public interface ChiTietPlaylistService extends CrudService<ChiTietPlaylist, Integer> {
		List<ChiTietPlaylist> findByPlaylist(Playlist pl);
}
