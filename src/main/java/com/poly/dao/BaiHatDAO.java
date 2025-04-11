package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.BaiHat;

public interface BaiHatDAO extends JpaRepository<BaiHat, Integer>{

	@Query("SELECT bh FROM BaiHat bh WHERE bh.tenBaiHat LIKE?1 OR bh.theLoai.tenTheLoai LIKE?1 OR bh.ngheSi.tenNgheSi LIKE?1")
		List<BaiHat> searchBaiHat(String keyword);

    @Query("SELECT bh FROM BaiHat bh LEFT JOIN bh.danhGias dg " +
            "GROUP BY bh.baiHatId, bh.hinhBaiHat, bh.loiBaiHat, bh.ngayTao, bh.ngheSi, bh.spotifyId, bh.tenBaiHat, bh.theLoai, bh.trangThai " +
            "ORDER BY AVG(dg.diemSo) DESC")
	List<BaiHat> getTop10(Pageable pageable);
}
