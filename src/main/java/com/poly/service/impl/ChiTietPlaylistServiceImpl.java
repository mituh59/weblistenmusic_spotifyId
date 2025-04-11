package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ChiTietPlaylistDAO;
import com.poly.entity.ChiTietPlaylist;
import com.poly.entity.Playlist;
import com.poly.service.ChiTietPlaylistService;

@Service("ChiTietPlaylistService")
public class ChiTietPlaylistServiceImpl implements ChiTietPlaylistService{
	@Autowired
	ChiTietPlaylistDAO dao;

	@Override
	public List<ChiTietPlaylist> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ChiTietPlaylist findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public ChiTietPlaylist create(ChiTietPlaylist entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(ChiTietPlaylist entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}

	@Override
	public List<ChiTietPlaylist> findByPlaylist(Playlist pl) {
		// TODO Auto-generated method stub
		return dao.findByPlaylist(pl);
	}
}
