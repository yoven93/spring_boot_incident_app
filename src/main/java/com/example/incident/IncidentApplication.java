package com.example.incident;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.incident.entities.User;
import com.example.incident.services.UserService;

@SpringBootApplication
public class IncidentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentApplication.class, args);
	}

	
	
	@Bean
	public CommandLineRunner initialize(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		return (args) -> {
			User user1 = new User();
			user1.setUsername("yoven");
			user1.setPassword(bCryptPasswordEncoder.encode("1234"));
			user1.setRole("admin");
			
			User user2 = new User();
			user2.setUsername("jed");
			user2.setPassword(bCryptPasswordEncoder.encode("4321"));
			user2.setRole("user");
			
			userService.createUser(user1);
			userService.createUser(user2);
		};
	}

}
