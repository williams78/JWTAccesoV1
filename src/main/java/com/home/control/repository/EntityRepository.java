package com.home.control.repository;

import java.util.List;
import java.util.Optional;

import com.home.control.model.FieldsString;
import com.home.control.model.FieldsValues;
import com.home.control.model.FieldsValuesS;



public interface EntityRepository {

	<T> List<T> getAllRecords(Class<T> clase);
	<T> int SaveRecord(T save); 
	<T> Optional<T> FindByRecord(FieldsValues[] object, Class<T> clase);
	<T> List<T> FindByRecords(FieldsValues[] object, Class<T> clase);
	<T> List<T> getRecordsContaning( Class<T> clazz , FieldsString[] object);
	<T> int UpdateRecord(T update, FieldsValues[] object);
	<T> T FindUserName(String userName, Class<?> clase);
	<T> Optional<String> FindByRecordsString(FieldsValuesS[] object, Class<T> clase);
}
