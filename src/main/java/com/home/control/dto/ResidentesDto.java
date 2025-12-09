package com.home.control.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentesDto {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String vivienda;
	
}
