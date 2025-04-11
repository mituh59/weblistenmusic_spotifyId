package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.dao.AlbumDAO;
import com.poly.entity.Album;
import com.poly.service.AlbumService;

@Service("AlbumService")
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	AlbumDAO dao;

	@Override
	public List<Album> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Album findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public Album create(Album entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public void update(Album entity) {
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
	public List<Album> getRandomAlbum(Integer r) {
	    // Create PageRequest with page size r and the first page (0-indexed)
	    PageRequest page = PageRequest.of(0, r);
	    // Return the list of albums from the Page
	    return dao.getRandomAlbum(page);
	}
    
}
