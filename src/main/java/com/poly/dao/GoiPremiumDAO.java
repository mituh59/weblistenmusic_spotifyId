package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.GoiPremium;

import java.util.Optional;

public interface GoiPremiumDAO extends JpaRepository<GoiPremium, Integer>{

    Optional<GoiPremium> findByGia(Double gia);

}
