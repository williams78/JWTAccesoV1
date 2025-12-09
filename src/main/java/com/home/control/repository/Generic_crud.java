package com.home.control.repository;

import java.util.List;

public interface Generic_crud<D> {

	List<D> getAll();
	D findById(Long id);
	List<D> getNameContaning(String data);
	String save(D d);
	int update(D d);
	List<D> getStatus(boolean data);
	
}
