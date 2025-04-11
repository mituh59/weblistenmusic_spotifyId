package com.poly.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.DanhGia;

public interface DanhGiaDAO extends JpaRepository<DanhGia, Integer>{
	List<DanhGia> findByBaiHat_BaiHatId(Integer baiHatId);

    @Query("SELECT AVG(d.diemSo) FROM DanhGia d WHERE d.baiHat.baiHatId = :baiHatId")
    Optional<Double> getDiemTrungBinh(@Param("baiHatId") Integer baiHatId);
    
    boolean existsByUser_UsernameAndBaiHat_BaiHatId(String username, Integer baiHatId);

}
