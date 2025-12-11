package com.home.control.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.home.control.dto.ProveedorMapper;
import com.home.control.dto.ProveedorMapperImpl;
import com.home.control.dto.ProveedoresDto;
import com.home.control.entity.Proveedores;
import com.home.control.repository.CustomRepository;
import com.home.control.repository.Generic_crud;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Proveedores_Service implements Generic_crud<ProveedoresDto>{

	private final ProveedorMapper pm = new ProveedorMapperImpl();
	private final CustomRepository repository;
	
	@Override
	public List<ProveedoresDto> getAll() {
		try {
			return pm.proveedoresEntitiesToProveedoresDtos(repository.getAllRecords(Proveedores.class));
		}catch(EmptyResultDataAccessException e) {
			return null;	
		}
		
	}

	@Override
	public ProveedoresDto findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public List<ProveedoresDto> getNameContaning(Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(ProveedoresDto d) {
		int g =  repository.SaveRecord(pm.proveedoresDtoToproveedores(d));
		return (g==1)?"Guardado con Exito":"Error al Guardar";
	}

	@Override
	public int update(ProveedoresDto d) {
		// TODO Auto-generated method stub
		return 0;
	}



		@Override
	public List<ProveedoresDto> getStatus(Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
