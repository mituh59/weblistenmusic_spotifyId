package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.DanhGiaDAO;
import com.poly.entity.DanhGia;
import com.poly.service.DanhGiaService;

@Service("DanhGiaService")
public class DanhGiaServiceImpl implements DanhGiaService{
	@Autowired
	DanhGiaDAO dao;

	@Override
	public List<DanhGia> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public DanhGia findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public DanhGia create(DanhGia entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(DanhGia entity) {
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
	    public List<DanhGia> getDanhGiaByBaiHat(Integer baiHatId) {
	        return dao.findByBaiHat_BaiHatId(baiHatId);
	    }

	    @Override
	    public Double getDiemTrungBinh(Integer baiHatId) {
	        return dao.getDiemTrungBinh(baiHatId).orElse(0.0);
	    }
	    @Override
	    public DanhGia save(DanhGia danhGia) {
	        return dao.save(danhGia);
	    }
}
