package com.home.control.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.home.control.model.FieldsValues;
import com.home.control.model.FieldsValuesS;



import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRepository implements EntityRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private String[] fieldsp;
	private Field[] Fieldsp;
	private Object[] fieldspValue;
	private int SaveOrUpdate = 1;
	
	@Override
	public <T> List<T> getAllRecords(Class<T> clase) {
		try {
		return jdbcTemplate.query("select * from " + clase.getSimpleName() , new LombokRowMapper<T>( clase ));
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public <T> List<T> getAllRecordStatus(Class<T> clazz, FieldsValues[] object) { 
		String sql = "Select * from " + clazz.getSimpleName() + " where " + object[0].getNamefield() +"=" + object[0].getValue() + " and "+ object[1].getNamefield() +" >= to_date('" + object[1].getValue() + "','dd/mm/yyyy') and " + object[2].getNamefield() + " <= to_date('" + object[2].getValue() + "','dd/mm/yyyy')";
		return jdbcTemplate.query(sql , new LombokRowMapper<T>(clazz));
	}

	@Override
	public <T> int SaveRecord(T save) {
		SaveOrUpdate = 1;
	    getFieldsCustom(save);
       		
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ")
            .append( save.getClass().getSimpleName() )
            .append( "(" ).append( String.join( ",", fieldsp) ).append( ")" )
            .append( " VALUES " )
            .append( "(" ).append( String.join( ",", Collections.nCopies( fieldsp.length, "?") ) ).append( ")" );
		
       
		return jdbcTemplate.update(sql.toString(), fieldspValue);
	}

	@Override
	public <T> Optional<T> FindByRecord(FieldsValues[] object, Class<T> clase) {
		
		String sql = "Select * from " + clase.getSimpleName() + " where " + object[0].getNamefield() + " = " + object[0].getValue();
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new LombokRowMapper<>(clase)));
		}catch(EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		
		 

	}

	private class LombokRowMapper<T> implements RowMapper<T>{

		private Class<?> clazz = null;
		
		public LombokRowMapper(Class<?> clazz) {
			super();
			this.clazz = clazz;
		}

		
		@SuppressWarnings("unchecked")
		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			try {
				
				Method builderMethod = clazz.getMethod("builder");
				
				if(builderMethod == null) return null;
				
				Object row = builderMethod.invoke(null);
				
                
				Method[] m = row.getClass().getDeclaredMethods();
				
                for ( int i=0; i<m.length; i++ ) {
                	 	
                	int pos = -1;
                	
                    try { 
                    	
                    	
                    	pos = rs.findColumn( m[i].getName() );
                    	
                     } catch ( SQLException ex ) {  }

                    if ( pos != -1 ) {
                    	
                        Object fieldValue = rs.getObject( pos );
                        	
                        m[i].invoke( row, fieldValue );
                        
                    }
                }   
                return  (T) row.getClass().getDeclaredMethod("build").invoke(row);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }	
			return null;
		}
		}
	
	@Override
	public <T> List<T> FindByRecords(FieldsValues[] object, Class<T> clase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getRecordsContaning(Class<T> clazz, FieldsValues[] object) {
		return jdbcTemplate.query("Select * from " + clazz.getSimpleName() + " where " + object[0].getNamefield() +" ilike '%" + object[0].getValue() + "%'", new LombokRowMapper<T>(clazz));
	}

	@Override
	public <T> int UpdateRecord(T update, FieldsValues[] object) {
        SaveOrUpdate = 0;
		
		getFieldsCustom(update);
		
   		StringBuilder sqle = new StringBuilder();
		StringBuilder sql = new StringBuilder();
        sql.append("update ")
            .append( update.getClass().getSimpleName() )
            .append( " set " );
		
        for (int i = 0; i<fieldsp.length; i++) {
		     
			if(!ValidationString(fieldsp[i].toString(), object)) {
				sql.append(fieldsp[i]).append("=").append("'").append(fieldspValue[i]).append("'");
			}
			if(i!=fieldsp.length-object.length && !ValidationString(fieldsp[i].toString(), object)) {
				sql.append(",");
			}
		 }
       
         for(int j=0; j<object.length; j++) {
        	 
        	 if(j==0) {
        		 sqle = sqle.append(" where ").append(object[j].getNamefield().toString()).append(" = ")
        				 .append(object[j].getValue().toString()); 
        	 }else {
        		 
        		 sqle.append(" and ").append(object[j].getNamefield()).append("=").append(object[j].getValue());
        	 }
        	 
         }
        
		sql.append(sqle);
              
		return jdbcTemplate.update(sql.toString());

	}

	@Override
	public <T> T FindUserName(String userName, Class<?> clase) {
		// TODO Auto-generated method stub
		return null;
	}

public <T> void getFieldsCustom(T clazz)  {
		
		Fieldsp = clazz.getClass().getDeclaredFields();
		fieldsp = new String [Fieldsp.length-SaveOrUpdate];
		fieldspValue = new Object[Fieldsp.length-SaveOrUpdate];
					
		for(int i=0; i<fieldsp.length ; i++) {
			
			
			fieldsp[i]= Fieldsp[i+SaveOrUpdate].getName();
			
			try {
				fieldspValue[i]=clazz.getClass().
				        getMethod(((Fieldsp[i+SaveOrUpdate].getType().equals(boolean.class))?"is":"get") +
				    		    Fieldsp[i+SaveOrUpdate].getName().substring(0,1).toUpperCase()+Fieldsp[i+SaveOrUpdate].getName().substring(1)).
				                invoke(clazz);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

private boolean ValidationString(String field, FieldsValues[] object) {
	boolean res = false;
	for(int i=0; i<object.length;i++) {
		
		if(object[i].getNamefield().equals(field)) {
			res = true;
		}
	}
	
	return res;
}

@Override
public <T> Optional<String> FindByRecordsString(FieldsValuesS[] object, Class<T> clase) {
	String sql = "Select gnumberhome from " + clase.getSimpleName() + " where " + object[0].getNamefield() + " = ?"    ;
	
	try {
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql,String.class,object[0].getId() ));
	}catch(EmptyResultDataAccessException e) {
		return Optional.empty();
	}
}

















}
