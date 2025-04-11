package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.GoiPremiumDAO;
import com.poly.entity.GoiPremium;
import com.poly.service.GoiPremiumService;

@Service("GoiPremiumService")
public class GoiPremiumServiceImpl implements GoiPremiumService{
	@Autowired
	GoiPremiumDAO dao;

	@Override
	public List<GoiPremium> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public GoiPremium findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public GoiPremium create(GoiPremium entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(GoiPremium entity) {
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
