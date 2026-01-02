package com.home.control.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.home.control.dto.ProductosDto;
import com.home.control.dto.ProductosMapper;
import com.home.control.dto.ProductosMapperImpl;
import com.home.control.entity.Productos;
import com.home.control.model.FieldsValues;
import com.home.control.repository.CustomRepository;
import com.home.control.repository.Generic_crud;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Productos_Service implements Generic_crud<ProductosDto>{
	
	private final CustomRepository repository;
	private final ProductosMapper pm = new ProductosMapperImpl();
	private final FieldsValues object[] = new FieldsValues[1];
	private final Field[] Fieldsp = Productos.class.getDeclaredFields();
	
	@Override
	public List<ProductosDto> getAll() {
		return pm.productosEntitiesToProductosDtos(repository.getAllRecords(Productos.class));
	}

	@Override
	public ProductosDto findById(Object id) {
		object[0] = new FieldsValues(id.toString(),Fieldsp[0].getName());
		Optional<Productos> p = repository.FindByRecord(object, Productos.class);
		return (p.isEmpty())?new ProductosDto():pm.productosToProductosDto(p);
	}

	@Override
	public List<ProductosDto> getNameContaning(Object data) {
		object[0] = new FieldsValues(Fieldsp[1].getName(),data.toString());
		return pm.productosEntitiesToProductosDtos(repository.getRecordsContaning(Productos.class,object));
	}


	@Override
	public String save(ProductosDto d) {
		int g = repository.SaveRecord(pm.productosDtoToproductos(d));
	    System.out.println(g);
		 return (g==1)?"Guardo con Exito":"Error al Guardar";
	}


	@Override
	public int update(ProductosDto d) {
		Productos ori = pm.productosDtoToproductos(d);
		object[0] = new FieldsValues(ori.getPddi().toString(),Fieldsp[0].getName());
		return repository.UpdateRecord(ori,object);
	}

	@Override
	public List<ProductosDto> getStatus(Object data) {
		
		return null;
	} 



	




}
