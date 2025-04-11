package com.poly.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "GoiPremium")
public class GoiPremium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goiId;
    
    @Column(name = "tenGoi", nullable = false)
    private String tenGoi;
    
    @Column(name = "thoiHan", nullable = false)
    private Integer thoiHan;
    
    @Column(name = "gia", nullable = false)
    private Double gia;
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;
    
    @JsonIgnore
    @OneToMany(mappedBy = "goiPremium")
    private List<ThanhToan> thanhToans;
    
    @JsonIgnore
    @OneToMany(mappedBy = "goiPremium")
    private List<UserPremium> userPremiums;
}
