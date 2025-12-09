package com.home.control.dto;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.control.model.Visitas;

@Mapper(componentModel = "sprint")
public interface VisitaMapper {
	
	@Mapping(source = "viid", target = "id")
	@Mapping(source = "viname", target = "name")
	@Mapping(source = "vivivienda", target = "vivienda")
	@Mapping(source = "vivehiculo", target = "vehiculo")
	@Mapping(source = "vianfitrion", target = "anfitrion")
	@Mapping(source = "viestado", target = "estado")
	@Mapping(source = "vicodigoqr", target = "codigoqr")
	@Mapping(source = "viinicio", target = "inicio")
	@Mapping(source = "vifin", target = "fin")
	VisitaDto visitanteToVisitanteDto(Visitas v); 
	
	List<VisitaDto> visitanteEntitiesToVisitanteDto(List<Visitas> visitantes);
	
	@Mapping(source = "id", target = "viid")
	@Mapping(source = "name", target = "viname")
	@Mapping(source = "vivienda", target = "vivivienda")
	@Mapping(source = "vehiculo", target = "vivehiculo")
	@Mapping(source = "anfitrion", target = "vianfitrion")
	@Mapping(source = "estado", target = "viestado")
	@Mapping(source = "codigoqr", target = "vicodigoqr")
	@Mapping(source = "inicio", target = "viinicio")
	@Mapping(source = "fin", target = "vifin")
	Visitas visitanteDtoToVisitante(VisitaDto v); 
	
	@Mapping(target = "id", expression = "java(v.get().getViid())")
	@Mapping(target = "name", expression = "java(v.get().getViname())")
	@Mapping(target = "vivienda", expression = "java(v.get().getVivivienda())")
	@Mapping(target = "vehiculo", expression = "java(v.get().getVivehiculo())")
	@Mapping(target = "anfitrion", expression = "java(v.get().getVianfitrion())")
	@Mapping(target = "estado", expression = "java(v.get().getViestado())")
	@Mapping(target = "codigoqr", expression = "java(v.get().getVicodigoqr())")
	@Mapping(target = "inicio", expression ="java(v.get().getViinicio())") 
	@Mapping(target = "fin", expression ="java(v.get().getVifin())") 
	VisitaDto visitanteToVisitanteDto(Optional<Visitas> v); 

}
