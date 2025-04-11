package com.poly.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "TheLoai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theLoaiId;

    @Column(nullable = false)
    private String tenTheLoai;
    
    @JsonIgnore
    @OneToMany(mappedBy = "theLoai")
    private List<BaiHat> baiHats;

}
