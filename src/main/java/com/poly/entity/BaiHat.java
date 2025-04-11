package com.poly.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "BaiHat")
public class BaiHat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baiHatId;
    
    @Column(name = "tenBaiHat", nullable = false)
    private String tenBaiHat;
    
    @ManyToOne
    @JoinColumn(name = "theLoaiId")
    private TheLoai theLoai;
    
    @Column(name = "loiBaiHat", nullable = true)
    private String loiBaiHat;
    
    @Column(name = "spotifyId")
    private String spotifyId;
    
    @Column(name = "hinhBaiHat", nullable = true)
    private String hinhBaiHat;
    
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngayTao")
    private Date ngayTao = new Date();
    
    @ManyToOne
    @JoinColumn(name = "ngheSiId", nullable = false)
    private NgheSi ngheSi;
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;
    
    @JsonIgnore
    @OneToMany(mappedBy = "baiHat")
    private List<DanhGia> danhGias;
    
    @JsonIgnore
    @OneToMany(mappedBy = "baiHat")
    private List<BinhLuan> binhLuans;
    
    @JsonIgnore
    @OneToMany(mappedBy = "baiHat")
    private List<LichSuNghe> lichSuNghes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "baiHat")
    private List<ChiTietPlaylist> chiTietPlaylists;
}
