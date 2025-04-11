package com.poly.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "DanhGia")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer danhGiaId;
    
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "baiHatId", nullable = false)
    private BaiHat baiHat;
    
    @Column(name = "diemSo", nullable = false)
    private int diemSo;
    
    @Column(name = "ngayTao", nullable = false)
    private Date ngayTao = new Date();
}
