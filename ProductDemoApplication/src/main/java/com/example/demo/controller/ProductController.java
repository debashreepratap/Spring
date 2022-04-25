package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Items;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/getAll")
	public Items getAllProducts() throws JsonMappingException, JsonProcessingException{
		 return productService.getPostsPlainJSON();
		
	}
}
