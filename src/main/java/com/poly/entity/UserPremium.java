package com.poly.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "UserPremium")
public class UserPremium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "goiId", nullable = false)
    private GoiPremium goiPremium;
    
    @Column(name = "ngayBatDau", nullable = false)
    private Date ngayBatDau;
    
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngayHetHan", nullable = false)
    private Date ngayHetHan;
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;


}
