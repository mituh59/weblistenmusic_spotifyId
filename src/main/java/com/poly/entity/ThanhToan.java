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
@Table(name = "ThanhToan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thanhToanId;
    
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "goiId", nullable = false)
    private GoiPremium goiPremium;
    
    @Column(name = "soTien", nullable = false)
    private Double soTien;
    
    @Column(name = "phuongThuc", nullable = false)
    private String phuongThuc;
    
    @Column(name = "ngayThanhToan", nullable = false)
    private Date ngayThanhToan = new Date();
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;
}
