package com.poly.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "Playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;
    
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;
    
    @Column(name = "tenPlaylist", nullable = false)
    private String tenPlaylist;
    
    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai;
    
    @Column(name = "hinhPlaylist", nullable = true)
    private String hinhPlaylist;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "playlist")
    private List<ChiTietPlaylist> chiTietPlaylists;
}
