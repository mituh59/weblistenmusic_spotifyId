package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.BinhLuan;

public interface BinhLuanDAO extends JpaRepository<BinhLuan, Integer>{
    List<BinhLuan> findByBaiHat_BaiHatId(Integer baiHatId);

}
