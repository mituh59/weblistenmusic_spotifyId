package com.poly.service;

import java.util.List;

import com.poly.entity.DanhGia;

public interface DanhGiaService extends CrudService<DanhGia, Integer>{
    List<DanhGia> getDanhGiaByBaiHat(Integer baiHatId);
    Double getDiemTrungBinh(Integer baiHatId);
    DanhGia save(DanhGia danhGia);  // Thêm phương thức save()

}
