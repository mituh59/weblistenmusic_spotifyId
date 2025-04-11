package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.LichSuNgheDAO;
import com.poly.entity.LichSuNghe;
import com.poly.service.LichSuNgheService;

@Service("LichSuNgheService")
public class LichSuNgheServiceImpl implements LichSuNgheService{
	@Autowired
	LichSuNgheDAO dao;

	@Override
	public List<LichSuNghe> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public LichSuNghe findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public LichSuNghe create(LichSuNghe entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(LichSuNghe entity) {
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
