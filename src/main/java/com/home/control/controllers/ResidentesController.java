package com.home.control.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.home.control.dto.VisitaDto;
import com.home.control.service.Visitante_Service;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acceso/residentes")
public class ResidentesController {

	private final Visitante_Service service;
	
	@GetMapping("/listAll")
	public ResponseEntity<List<VisitaDto>> findListVisitasAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<VisitaDto>> findListVisitasStatus(@RequestParam(required = true) Object data){
		return ResponseEntity.ok(service.getStatus(data));
	}
	
	@GetMapping("/findId")
	public ResponseEntity<VisitaDto> findId(@RequestParam(required = true) Object id){
		return ResponseEntity.ok(service.findById(id));
	} 
	 
	@GetMapping("/contaning")
	public ResponseEntity<List<VisitaDto>> ContaningName(@RequestParam(required = true) Object data){
		return ResponseEntity.ok(service.getNameContaning(data));
	} 
	
	@PostMapping("/save")
	public ResponseEntity<String> SaveVisitante(@RequestBody VisitaDto visitanteDto){
		return ResponseEntity.ok(service.save(visitanteDto));
	} 
	
	@PostMapping("/update")
	public ResponseEntity<Integer> UpdateVisita(@RequestBody VisitaDto visitanteDto){
		return ResponseEntity.ok(service.update(visitanteDto));
	}
	
}
