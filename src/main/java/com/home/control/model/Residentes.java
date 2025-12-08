package com.home.control.model;

import com.home.control.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="residentes")
public class Residentes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rsid;
	private String rsname;
	private String rsphone;
	private String rsemail;
	private String rsvivienda;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rsid", unique= true)
	private User user;
	
	
	
	
}
