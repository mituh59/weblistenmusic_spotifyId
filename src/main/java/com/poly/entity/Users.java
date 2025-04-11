package com.poly.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Users")
public class Users {
    @Id
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "hoTen")
    private String hoTen;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "hinhAnh")
    private String hinhAnh;
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;
    
    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Roles role;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ThanhToan> thanhToans;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserPremium> userPremiums;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<DanhGia> danhGias;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BinhLuan> binhLuans;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<LichSuNghe> lichSuNghes;
    
    
}
