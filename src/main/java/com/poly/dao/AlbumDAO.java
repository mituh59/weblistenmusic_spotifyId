package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Album;

public interface AlbumDAO extends JpaRepository<Album, Integer>{
	@Query("SELECT a FROM Album a ORDER BY RAND()")
	List<Album> getRandomAlbum(Pageable pageable);

}
