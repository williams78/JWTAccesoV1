package com.home.control.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.home.control.interfaz.IAuthentication;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acceso/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

	
	 @GetMapping("/dashboard")
	    public String adminDashboard() {
	        return "Bienvenido ADMIN";
	    }
	
	
}

