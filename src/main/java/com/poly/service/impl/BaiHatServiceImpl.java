package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.poly.dao.BaiHatDAO;
import com.poly.entity.BaiHat;
import com.poly.service.BaiHatService;

@Service("BaiHatService")
public class BaiHatServiceImpl implements BaiHatService{
	@Autowired
	BaiHatDAO dao;

	@Override
	public List<BaiHat> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public BaiHat findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public BaiHat create(BaiHat entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(BaiHat entity) {
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
	public List<BaiHat> searchBaiHat(String keyword) {
		List<BaiHat> list = dao.searchBaiHat(keyword);
		return list;
	}

	@Override
	public List<BaiHat> getTop10(Integer r) {
		// Create PageRequest with page size r and the first page (0-indexed)
	    PageRequest page = PageRequest.of(0, r);
	    // Return the list of albums from the Page
	    return dao.getTop10(page);
	}

}
