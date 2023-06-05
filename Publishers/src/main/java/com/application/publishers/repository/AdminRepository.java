package com.application.publishers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.publishers.model.User;

public interface AdminRepository extends JpaRepository<User,Long>{
	
	User findOneByEmailAndPassword(String email, String password);

	User findByEmail(String email);
}
