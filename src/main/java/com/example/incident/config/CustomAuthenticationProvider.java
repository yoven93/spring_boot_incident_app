package com.example.incident.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.incident.entities.User;
import com.example.incident.services.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public CustomAuthenticationProvider(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userService.findUserByUsername(username);

		if (user != null) {

			// Get user authorities
			List<GrantedAuthority> authorities = getAuthorities(user);

			// Get user's stored password
			String encodedPassword = user.getPassword();

			// Check if password inputed matches password stored for the user
			// Return token if they match
			if (bCryptPasswordEncoder.matches(password, encodedPassword)) {
				return new UsernamePasswordAuthenticationToken(user.getUserId(), encodedPassword, authorities);
			}
		}
		
		// If user is not found or password does not match, throw error
		throw new BadCredentialsException("Invalid Credentials");
	}

	private List<GrantedAuthority> getAuthorities(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();

		// All users have the user role
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		// Checks if user has the admin role
		if (user.getRole().equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
