package com.home.control.dto;


import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.home.control.entity.Productos;


@Mapper(componentModel = "spring")
public interface ProductosMapper {
	
	@Mapping(source = "pddi", target = "di")
	@Mapping(source = "pdnombre", target = "nombre")
	@Mapping(source = "pdpresentacion", target = "presentacion")
	@Mapping(source = "pdcaducida", target = "caducida")
	@Mapping(source = "pdcategoria", target = "categoria")
	@Mapping(source = "pddescripcion", target = "descripcion")
	@Mapping(source = "pdexistencia", target = "existencia")
	@Mapping(source = "pdprecio", target ="precio") 
	@Mapping(source = "pdvalortotal", target ="valortotal") 
	@Mapping(source = "pdproveedor", target ="proveedor") 
	@Mapping(source = "pdingreso", target ="ingreso") 
	@Mapping(source = "pdobservaciones", target = "observaciones")
	ProductosDto productosToProductosDto(Productos p); 
	
	
	/*@Mapping(source = "pdid", target = "id")
	@Mapping(source = "pdnombre", target = "dnombre")
	@Mapping(source = "pdpresentacion", target = "presentacion")
	@Mapping(source = "pdcaducida", target = "caducida")
	@Mapping(source = "pdcategoria", target = "categoria")
	@Mapping(source = "pddescripcion", target = "descripcion")
	@Mapping(source = "pdexistencia", target = "existencia")
	@Mapping(source = "pdprecio", target ="precio") 
	@Mapping(source = "pdvalortotal", target ="valortotal") 
	@Mapping(source = "pdproveedor", target ="proveedor") 
	@Mapping(source = "pdingreso", target ="ingreso") 
	@Mapping(source = "pdobservaciones", target = "observaciones")*/
    List<ProductosDto> productosEntitiesToProductosDtos(List<Productos> productosEntities);
	
	@Mapping(source = "di",	   target = "pddi")
	@Mapping(source = "nombre", target = "pdnombre")
	@Mapping(source = "presentacion", target = "pdpresentacion")
	@Mapping(source = "caducida", target = "pdcaducida")
	@Mapping(source = "categoria", target = "pdcategoria")
	@Mapping(source = "descripcion", target = "pddescripcion")
	@Mapping(source = "existencia", target = "pdexistencia")
	@Mapping(source = "precio", target ="pdprecio") 
	@Mapping(source = "valortotal", target ="pdvalortotal") 
	@Mapping(source = "proveedor", target ="pdproveedor") 
	@Mapping(source = "ingreso", target ="pdingreso") 
	@Mapping(source = "observaciones", target = "pdobservaciones")
    Productos productosDtoToproductos(ProductosDto productos);

	@Mapping(target = "di", expression = "java(p.get().getPddi())")
	@Mapping(target = "nombre", expression = "java(p.get().getPdnombre())")
	@Mapping(target = "presentacion", expression = "java(p.get().getPdpresentacion())")
	@Mapping(target = "caducida", expression = "java(p.get().getPdcaducida())")
	@Mapping(target = "categoria", expression = "java(p.get().getPdcategoria())")
	@Mapping(target = "descripcion", expression = "java(p.get().getPddescripcion())")
	@Mapping(target = "existencia", expression = "java(p.get().getPdexistencia())")
	@Mapping(target = "precio", expression ="java(p.get().getPdprecio())") 
	@Mapping(target = "valortotal", expression ="java(p.get().getPdvalortotal())") 
	@Mapping(target = "proveedor", expression ="java(p.get().getPdproveedor())") 
	@Mapping(target = "ingreso", expression ="java(p.get().getPdingreso())") 
	@Mapping(target = "observaciones", expression = "java(p.get().getPdobservaciones())")
	ProductosDto productosToProductosDto(Optional<Productos> p); 
	
	
	

	
	
}
