package com.sonarqube.demo;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

	public String getMessage() {
		String str="hello there";
		Object obj=getObject();
		System.out.println(obj.toString());		
				
		return str;
	}

	private Object getObject() {
		return "xyz";
	}

}
