package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CheckStatusService;

@RestController
public class CheckStatusController {
	
	@Autowired
	CheckStatusService service;
	
	@GetMapping("/check")
	public String checkStatus(@RequestParam(name="tid")String trackingNumber) {
		return service.checkStatus(trackingNumber);
	}
	
	

}
