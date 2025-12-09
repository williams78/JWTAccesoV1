package com.home.control.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitaDto {
	
	private Long id ;
	private String name; 
	private String vivienda;
	private String vehiculo;
	private int anfitrion;
	private boolean estado; 
	private String codigoqr;
	@JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date inicio;
	@JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date fin;
	

}
