package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JWTService;
import com.example.demo.controller.AuthenticationRequest;
import com.example.demo.controller.AuthenticationResponse;
import com.example.demo.controller.RegisterRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class AuthenticationService {
	
	@Autowired
	private  UserRepo repo;
	
	@Autowired
	private  PasswordEncoder encoder;
	
	@Autowired
	private  JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest register) {
	var user=new User();
	user.setFirtsName(register.getFirstName());
	user.setLastName(register.getLastName());
	user.setEmail(register.getEmail());
	user.setPassword(encoder.encode(register.getPassword()));
	user.setRole(Role.user);
	
	repo.save(user);
		
	var jwtToken = jwtService.generateToken(user);
	var auth=new AuthenticationResponse();
	auth.setToken(jwtToken);
	
	return auth;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest register) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(register.getEmail(), register.getPassword())
				
				);
		
		var user = repo.findByEmail(register.getEmail()).orElseThrow(()-> new UsernameNotFoundException("Not found"));
		
		var jwtToken = jwtService.generateToken(user);
		var auth=new AuthenticationResponse();
		auth.setToken(jwtToken);
		
		return auth;
	}

	
	
}
