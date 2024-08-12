package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:9091")
public class AuthenticationController {
	
	@Autowired
	private  AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest register){
		return ResponseEntity.ok(authenticationService.register(register));
	}
	
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest register){
		return ResponseEntity.ok(authenticationService.authenticate(register));
	}

}
