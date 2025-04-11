package com.poly.service;

import java.util.List;

import com.poly.entity.Album;

public interface AlbumService extends CrudService<Album, Integer>{
	List<Album> getRandomAlbum(Integer r);
}
