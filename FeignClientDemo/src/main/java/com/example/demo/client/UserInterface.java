package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.UserData;

@FeignClient(url="https://jsonplaceholder.typicode.com",name="USER-CLIENT")
public interface UserInterface { 	
	
	@GetMapping("/users")
   public List<UserData> getUsers();
}
