package com.home.control.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="generales")
public class Generales {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gid;
	private String gname;
	private String gnumberhome;
	private Long iduser;
	
}
