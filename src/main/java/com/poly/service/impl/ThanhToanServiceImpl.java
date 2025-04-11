package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ThanhToanDAO;
import com.poly.entity.ThanhToan;
import com.poly.service.ThanhToanService;

@Service("ThanhToanService")
public class ThanhToanServiceImpl implements ThanhToanService{
	@Autowired
	ThanhToanDAO dao;

	@Override
	public List<ThanhToan> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ThanhToan findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public ThanhToan create(ThanhToan entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(ThanhToan entity) {
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
