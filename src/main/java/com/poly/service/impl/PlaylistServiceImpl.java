package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.PlayListDAO;
import com.poly.entity.Playlist;
import com.poly.service.PlayListService;

@Service("PlayListService")
public class PlaylistServiceImpl implements PlayListService{
	@Autowired
	PlayListDAO dao;

	@Override
	public List<Playlist> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Playlist findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public Playlist create(Playlist entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(Playlist entity) {
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
}
