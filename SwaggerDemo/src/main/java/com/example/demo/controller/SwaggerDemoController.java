package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class SwaggerDemoController {

	Map<String, Contact> contacts = new HashMap<>();

	@GetMapping("/getAll")
	public List<Contact> getAllContacts() {
		return new ArrayList<Contact>(contacts.values());
	}

	
	@ApiOperation(value="Find Contacts by Id",
			notes="Provide an id to get the Contact details",
			response=Contact.class)
	@GetMapping("/{id}")
	public Contact getContact(@PathVariable String id) {
		return contacts.get(id);
	}

	@PostMapping("/addContact")
	public Contact addContact(@RequestBody Contact contact) {
		contacts.put(contact.getId(), contact);
		return contact;
	}
}
