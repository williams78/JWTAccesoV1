package com.home.control.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.control.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query(value ="Select id,username,password, role, email, phone from users where (username=:nombre or email=:nombre or phone=:nombre)", nativeQuery = true)  
	Optional<User>  findByusername(@Param("nombre") String nombre);
	
	@Query(value ="Select id,username,password, role, email, phone from users where (username=:nombre or email=:email or phone=:phone)", nativeQuery = true)  
	Optional<User>  findByFiels(@Param("nombre") String nombre,@Param("email") String email, @Param("phone") String phone);
	
	
}
