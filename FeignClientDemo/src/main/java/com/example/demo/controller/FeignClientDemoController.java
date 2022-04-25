package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.UserInterface;
import com.example.demo.model.UserData;

@RestController
public class FeignClientDemoController {

	@Autowired
	UserInterface client;
	
	@GetMapping("/getAllUsers")
	public List<UserData> getUsers(){
		return client.getUsers();
	}
}
