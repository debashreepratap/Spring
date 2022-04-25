package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/users",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces="application/json")
	public ResponseEntity getUserEntity(@RequestParam(value="files")MultipartFile[] files) throws Exception {
		for(MultipartFile file:files) {
			userService.saveUser(file);
			}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value="/users",produces="application/json")
	public CompletableFuture<ResponseEntity> findAllUsers(){
		return userService.getAllUsers().thenApply(ResponseEntity::ok);
	}

	@GetMapping(value="/getUsersByThread",produces="application/json")
	public ResponseEntity getUsers() {
		CompletableFuture<List<User>> users1=userService.getAllUsers();
		CompletableFuture<List<User>> users2=userService.getAllUsers();
		CompletableFuture<List<User>> users3=userService.getAllUsers();
		CompletableFuture.allOf(users1,users2,users3);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
}
