package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.UserPremiumDAO;
import com.poly.entity.UserPremium;
import com.poly.service.UserPremiumService;

@Service("UserPremiumService")
public class UserPremiumServiceImpl implements UserPremiumService{
	@Autowired
	UserPremiumDAO dao;

	@Override
	public List<UserPremium> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public UserPremium findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public UserPremium create(UserPremium entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(UserPremium entity) {
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
