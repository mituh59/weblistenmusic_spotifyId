package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "ChiTietPlaylist")
public class ChiTietPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = false)
    private Playlist playlist;
    
    @ManyToOne
    @JoinColumn(name = "baiHatId", nullable = false)
    private BaiHat baiHat;
    
    @Column(name = "soThuTu", nullable = false)
    private int soThuTu;
}
