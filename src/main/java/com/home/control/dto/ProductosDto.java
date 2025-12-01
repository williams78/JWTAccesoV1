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
public class ProductosDto {

	private Long di;
	private String nombre;
	private String presentacion;
	@JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date caducida;
	private String categoria;
	private String descripcion;
	private Long existencia;
	private double precio;
	private double valortotal;
	private String proveedor;
	@JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
	private Date ingreso;
	private String observaciones;
	
	
	
	
}
