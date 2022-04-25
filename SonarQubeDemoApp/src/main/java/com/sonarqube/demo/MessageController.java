package com.sonarqube.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	
	public String getMessage() {
		return messageService.getMessage();
	}

}
