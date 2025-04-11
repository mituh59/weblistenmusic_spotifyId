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
@Table(name = "LichSuNghe")
public class LichSuNghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lsNgheId;
    
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "baiHatId", nullable = false)
    private BaiHat baiHat;
    
    @Column(name = "thoiGianNghe", nullable = false)
    private Date thoiGianNghe = new Date();
}
