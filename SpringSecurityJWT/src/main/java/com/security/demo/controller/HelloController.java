package com.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.model.AuthenticationRequest;
import com.security.demo.model.AuthenticationResponse;
import com.security.demo.model.MyUserDetailsService;
import com.security.demo.util.JWTUtil;

@RestController
public class HelloController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	MyUserDetailsService myUserDetailService;
	
	@Autowired
	JWTUtil jwtUtil;

	@RequestMapping("/hello")
	public String helloo() {
		return "Hello Java";
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception{
		try {
		 authManager.authenticate(
				   new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username or password",e);
			}
		
	
	final UserDetails userDetails =myUserDetailService.loadUserByUsername(request.getUsername());
	final String jwt = jwtUtil.generateToken(userDetails);
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
