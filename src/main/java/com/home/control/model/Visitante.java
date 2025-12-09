package com.home.control.model;

import java.util.Date;

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
@Table(name="visitas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long viid ;
	private String viname; 
	private String vivivienda;
	private String vivehiculo;
	private int vianfitrion;
	private boolean viestado; 
	private String vicodigoqr;
	private Date viinicio;
	private Date vifin;
	
}
