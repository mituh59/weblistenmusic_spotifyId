package com.poly.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Users;

public interface UsersDAO extends JpaRepository<Users, String>{
    Optional<Users> findByEmail(String email);

}
