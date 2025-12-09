package com.home.control.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.control.model.Residentes;

@Mapper(componentModel = "spring")
public interface ResidentesMapper {

	@Mapping(source = "rsid", target = "id")
	@Mapping(source = "rsname", target = "name")
	@Mapping(source = "rsphone", target = "phone")
	@Mapping(source = "rsemail", target = "email")
	@Mapping(source = "rsvivienda", target = "vivienda")
	ResidentesDto residentesToResidentesDto(Residentes r); 
	
}
