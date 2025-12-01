package com.home.control.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.control.entity.Proveedores;

@Mapper
public interface ProveedorMapper {

	@Mapping(source = "pr_id", target = "id")
	@Mapping(source = "pr_nombre", target = "nombre")
	ProveedoresDto proveedoresToProveedoresDto(Proveedores productos); 
	
	List<ProveedoresDto> proveedoresEntitiesToProveedoresDtos(List<Proveedores> proveedoresEntities);
	
	@Mapping(source = "id",	   target = "pr_id")
	@Mapping(source = "nombre", target = "pr_nombre")
	Proveedores proveedoresDtoToproveedores(ProveedoresDto proveedores); 
	
}
