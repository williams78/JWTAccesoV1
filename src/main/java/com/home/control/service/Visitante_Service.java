package com.home.control.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.home.control.dto.VisitaDto;
import com.home.control.dto.VisitaMapper;
import com.home.control.dto.VisitaMapperImpl;
import com.home.control.model.FieldsString;
import com.home.control.model.FieldsValues;
import com.home.control.model.Visitas;
import com.home.control.repository.CustomRepository;
import com.home.control.repository.Generic_crud;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Visitante_Service implements Generic_crud<VisitaDto>{

	private final CustomRepository customRepository;
	private final VisitaMapper vimp = new VisitaMapperImpl();
	private final FieldsValues object[] = new FieldsValues[1];
	private final FieldsString obj[] = new FieldsString[1];
	private final Field[] Fieldsp = Visitas.class.getDeclaredFields();
	
	@Override
	public List<VisitaDto> getAll() {
		return vimp.visitanteEntitiesToVisitanteDto(customRepository.getAllRecords(Visitas.class));
	}
	
	@Override
	public VisitaDto findById(Long id) {
		object[0] = new FieldsValues(id,Fieldsp[0].getName());
		Optional<Visitas> p = customRepository.FindByRecord(object, Visitas.class);
		return (p.isEmpty())?new VisitaDto():vimp.visitanteToVisitanteDto(p);
	}
	
	@Override
	public List<VisitaDto> getNameContaning(String data) {
		obj[0] = new FieldsString(data,Fieldsp[1].getName(),false);
		return vimp.visitanteEntitiesToVisitanteDto(customRepository.getRecordsContaning(Visitas.class,obj));
	}
	
	@Override
	public String save(VisitaDto d) {
		int g = customRepository.SaveRecord(vimp.visitanteDtoToVisitante(d));
	    return (g==1)?"Guardo con Exito":"Error al Guardar";
	}
	
	@Override
	public int update(VisitaDto d) {
		Visitas ori = vimp.visitanteDtoToVisitante(d);
		object[0] = new FieldsValues(ori.getViid(),Fieldsp[0].getName());
		return customRepository.UpdateRecord(ori,object);
	}

	@Override
	public List<VisitaDto> getStatus(boolean data) {
		obj[0] = new FieldsString("Arroz",Fieldsp[5].getName(),data);
		return vimp.visitanteEntitiesToVisitanteDto(customRepository.getRecordsStatus(Visitas.class, obj));
	}
	
	


	
	
}
