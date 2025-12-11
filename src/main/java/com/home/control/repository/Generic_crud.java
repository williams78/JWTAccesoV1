package com.home.control.repository;

import java.util.List;





public interface Generic_crud<D> {

	List<D> getAll();
	D findById(Object id);
	List<D> getNameContaning(Object data);
	String save(D d);
	int update(D d);
	List<D> getStatus(Object data);
	
}
