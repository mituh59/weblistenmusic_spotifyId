package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.RolesDAO;
import com.poly.entity.Roles;
import com.poly.service.RolesService;

@Service("RolesService")
public class RolesServiceImpl implements RolesService{
	@Autowired
	RolesDAO dao;

	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Roles findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public Roles create(Roles entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(Roles entity) {
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
