package com.example.incident.services;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.incident.entities.User;
import com.example.incident.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/**
	 * Find user by username
	 */
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * Create user
	 */
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
