package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.TheLoaiDAO;
import com.poly.entity.TheLoai;
import com.poly.service.TheLoaiService;

@Service("TheLoaiService")
public class TheLoaiServiceImpl implements TheLoaiService{
	@Autowired
	TheLoaiDAO dao;

	@Override
	public List<TheLoai> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public TheLoai findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public TheLoai create(TheLoai entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(TheLoai entity) {
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
