package com.home.control.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.control.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query(value ="Select usid,username,password, role, usstatus, rsname, rsemail, rsphone, rsvivienda from usuarios join residentes on usid = rsid  where (username=:nombre or rsemail=:nombre or rsphone=:nombre)", nativeQuery = true)  
	Optional<User>  findByusername(@Param("nombre") String nombre);
	
	@Query(value ="Select usid,username,password, role, usstatus, rsname, rsemail, rsphone, rsvivienda from usuarios join residentes on usid = rsid "
			+ "where (username=:nombre or rsemail=:email or rsphone=:phone)", nativeQuery = true)  
	Optional<User>  findByFiels(@Param("nombre") String nombre,@Param("email") String email, @Param("phone") String phone);
	
	
}
