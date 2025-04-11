package com.poly.service;

import java.util.List;

import com.poly.entity.BaiHat;

public interface BaiHatService extends CrudService<BaiHat, Integer>{
	List<BaiHat> searchBaiHat(String keyword);
	
	List<BaiHat> getTop10(Integer r);
}
