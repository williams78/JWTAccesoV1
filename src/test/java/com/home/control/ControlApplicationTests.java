package com.home.control;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.control.dto.ProductosDto;
import com.home.control.repository.Generic_crud;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControlApplicationTests {

	@Autowired
	Generic_crud<ProductosDto> crud;
	
	
	
	@Test
	void contextLoads() {
		    
		ProductosDto p = new ProductosDto();
		p = crud.findById(6L);
		
		
		System.out.println(p.getNombre() + p.getDi() + p.getPresentacion());
		
		List<ProductosDto> pd  = crud.getAll();
		
		for(ProductosDto f : pd) {
			System.out.println(f.getNombre() + f.getDi() + f.getPresentacion());
		}
		
		ProductosDto dto = new ProductosDto();
		
	
		dto.setNombre("Clarasol");
		dto.setPresentacion("BOX101");
		
		System.out.println((crud.save(dto)==1)?"Exito":"Fracaso");
		
	}

	  /*public ProductosDto createProductosDto(Productos productos) {
	        return productosMapper.productosToProductosDto(productos);
	    }*/
	
}
