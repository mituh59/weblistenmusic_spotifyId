package com.poly.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.BinhLuanDAO;
import com.poly.entity.BinhLuan;
import com.poly.service.BinhLuanService;

@Service("BinhLuanService")
public class BinhLuanServiceImpl implements BinhLuanService{
	@Autowired
	BinhLuanDAO dao;

	@Override
	public List<BinhLuan> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public BinhLuan findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public BinhLuan create(BinhLuan entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(BinhLuan entity) {
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
	    public List<BinhLuan> getCommentsByBaiHat(Integer baiHatId) {
	        return dao.findByBaiHat_BaiHatId(baiHatId);
	    }
	 @Override
	 public BinhLuan addComment(BinhLuan binhLuan) {
	     binhLuan.setNgayBinhLuan(new Date()); // Gán ngày bình luận hiện tại
	     return dao.save(binhLuan);
	 }
}
