package com.poly.service;

import java.util.List;

import com.poly.entity.BinhLuan;

public interface BinhLuanService extends CrudService<BinhLuan, Integer>{
    List<BinhLuan> getCommentsByBaiHat(Integer baiHatId);
    BinhLuan addComment(BinhLuan binhLuan);

}
