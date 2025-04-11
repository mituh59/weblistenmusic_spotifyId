package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Playlist;

public interface PlayListDAO extends JpaRepository<Playlist, Integer>{

}
