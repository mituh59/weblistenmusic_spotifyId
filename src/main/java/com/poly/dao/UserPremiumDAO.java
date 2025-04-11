package com.poly.dao;

import com.poly.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.UserPremium;

import java.util.Optional;

public interface UserPremiumDAO extends JpaRepository<UserPremium, Integer>{

    Optional<UserPremium> findFirstByUserOrderByIdDesc(Users user);
}
