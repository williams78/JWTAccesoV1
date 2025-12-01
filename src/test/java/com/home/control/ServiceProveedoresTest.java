package com.home.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.home.control.dto.ProductosDto;
import com.home.control.entity.Productos;
import com.home.control.entity.Proveedores;
import com.home.control.model.FieldsString;
import com.home.control.model.FieldsValues;
import com.home.control.repository.CustomRepository;
import com.home.control.service.Productos_Service;

@ExtendWith(MockitoExtension.class)
class ServiceProveedoresTest {


	@Mock
	CustomRepository customRepository;
	
	@InjectMocks
	Productos_Service productos_Service;
	
	@Test
	void testBuscarPorId() {
		
		FieldsValues[] fields = new FieldsValues[1];
		
		Proveedores p = new Proveedores();
		p.setPr_id(7L);
		
		fields[0]= new FieldsValues (p.getPr_id(),"pr_id");
				
		when(customRepository.FindByRecord(fields, Proveedores.class)).thenReturn(Optional.of(p));
		
		ProductosDto productos = productos_Service.findById(7L);
		assertTrue(productos != null);

			}
	
	@Test
	void testObtenerTodos() {
		
		Proveedores p1 = new Proveedores();
		Proveedores p2 = new Proveedores();
		
		p1.setPr_nombre("SABRITAS");
		p2.setPr_nombre("BARCEL");
		
		List<Proveedores> list = Arrays.asList(p1,p2);
		
		when(customRepository.getAllRecords(Proveedores.class)).thenReturn(list);
		
		List<Proveedores> actualUsers =customRepository.getAllRecords(Proveedores.class);

		System.out.println(actualUsers.get(0).getPr_nombre());
        // 4. Verify the results and interactions
        assertEquals(2, actualUsers.size());
        assertEquals("TDC", actualUsers.get(0).getPr_nombre());
        assertEquals("CLORO2", actualUsers.get(1).getPr_nombre());
		
        verify(customRepository).getAllRecords(Productos.class);
		

		
	}
	
	@Test
	void testCrearProveedores() {
		
		Proveedores p = new Proveedores();
		
		p.setPr_nombre("Gamesa");
		
		when(customRepository.SaveRecord(p)).thenReturn(1);
		
	    int returnedUser=customRepository.SaveRecord(p);
	    verify(customRepository, times(1)).SaveRecord(any(Proveedores.class));
	 
	    assertEquals(returnedUser,1);
		
	}
	
	@Test
	@Disabled
	void testActualizarProducto() {
		
		Field[] Fieldsp = Productos.class.getDeclaredFields(); 
		FieldsValues object[] = new FieldsValues[1];
		
		Productos p = new Productos();
		p.setPddi(3L);
		p.setPdcategoria("Dulces");
		p.setPddescripcion("Chocolate Amargo");
		p.setPdexistencia(100L);
		
		p.setPdnombre("Chocolate Blanco");
		p.setPdobservaciones("Ninguna");
		p.setPdprecio(5);
		p.setPdpresentacion("Box");
		p.setPdproveedor("Abuelita");
		p.setPdvalortotal(500);
		
		object[0] = new FieldsValues(3L,Fieldsp[0].getName());
		when(customRepository.UpdateRecord(p,object)).thenReturn(1);
		
	    int returnedUser=customRepository.UpdateRecord(p,object);
	    verify(customRepository, times(1)).UpdateRecord(p, object);
	 
	    assertEquals(returnedUser,1);
		
	}
	
	@Test
	@Disabled
	void testContienenProducto() {
		
		Productos p1 =  new Productos();
		p1.setPddi(1L);
		p1.setPdnombre("CLORO");
		Productos p2 =  new Productos();
		p2.setPddi(2L);
		p2.setPdnombre("Fabuloso");
        
		
		List<Productos> products = Arrays.asList(p1,p2);
		
		Field[] Fieldsp = Productos.class.getDeclaredFields(); 
		FieldsString object[] = new FieldsString[1];
		
		object[0] = new FieldsString("CLORO",Fieldsp[1].getName());
		when(customRepository.getRecordsContaning(Productos.class, object)).thenReturn(products);
		
		List<ProductosDto> foundProducts = productos_Service.getNameContaning("CLORO");
		
		assertEquals(2, foundProducts.size());
        assertEquals("Fabuloso", foundProducts.get(1).getNombre());
        verify(customRepository, times(1)).getRecordsContaning(Productos.class, object);
		
	}
	
	

}
