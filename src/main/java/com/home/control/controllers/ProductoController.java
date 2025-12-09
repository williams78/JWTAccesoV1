package com.home.control.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.control.dto.ProductosDto;
import com.home.control.service.Productos_Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inv/produc")
public class ProductoController {

	private final Productos_Service productos;
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductosDto>> getProductos(){
		return ResponseEntity.ok(productos.getAll());
	}
	
	@GetMapping("/findId")
	public ResponseEntity<ProductosDto> findProducto(@RequestParam(required = true) Long id){
		return ResponseEntity.ok(productos.findById(id));
	} 
	
	@GetMapping("/contaning")
	public ResponseEntity<List<ProductosDto>> ContaningProducto(@RequestParam(required = true) String data){
		return ResponseEntity.ok(productos.getNameContaning(data));
	} 
	
	@PostMapping("/save")
	public ResponseEntity<Integer> SaveProducto(@RequestBody ProductosDto productosDto){
		return ResponseEntity.ok(productos.save(productosDto));
	} 
	
	@PostMapping("/update")
	public ResponseEntity<Integer> UpdateProducto(@RequestBody ProductosDto productosDto){
		return ResponseEntity.ok(productos.update(productosDto));
	}
	
	 @GetMapping("/profile")
	    public String profile() {
	        return "Perfil de usuario";
	    }
}
