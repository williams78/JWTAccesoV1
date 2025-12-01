package com.home.control.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="productos")
public class Productos {

	@Id
	private Long pddi;
	private String pdnombre;
	private String pdpresentacion;
	private Date pdcaducida;
	private String pdcategoria;
	private String pddescripcion;
	private Long pdexistencia;
	private double pdprecio;
	private double pdvalortotal;
	private String pdproveedor;
	private Date pdingreso;
	private String pdobservaciones;
	
}
