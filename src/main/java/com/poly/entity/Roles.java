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
@Table(name = "Roles")
public class Roles {
	@Id
	private String roleId;
	
	@Column(name = "tenRole", nullable = false)
	private String tenRole;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Users> users;

}
