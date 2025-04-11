package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.NgheSiDAO;
import com.poly.entity.NgheSi;
import com.poly.service.NgheSiService;

@Service("NgheSiService")
public class NgheSiServiceImpl implements NgheSiService{
	@Autowired
	NgheSiDAO dao;

	@Override
	public List<NgheSi> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public NgheSi findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public NgheSi create(NgheSi entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(NgheSi entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}
}
