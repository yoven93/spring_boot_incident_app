package com.example.incident.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.incident.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	/**
	 * Find user by username
	 */
	public User findByUsername(String username);
}	
