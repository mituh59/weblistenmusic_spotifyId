package com.poly.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "NgheSi")
public class NgheSi {
    @Id
    private String ngheSiId;
    
    @Column(name = "tenNgheSi", nullable = false)
    private String tenNgheSi;
    
    @Column(name = "gioiThieu")
    private String gioiThieu;
    
    @Column(name = "hinhNgheSi", nullable = true)
    private String hinhNgheSi;
    
    @JsonIgnore
    @OneToMany(mappedBy = "ngheSi")
    private List<BaiHat> baiHats;
    
    @JsonIgnore
    @OneToMany(mappedBy = "ngheSi")
    private List<Album> albums;
}
