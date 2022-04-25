package com.sonarqube.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SonarQubeDemoAppApplicationTests {
	
	@Autowired
	MessageController messageController;

	@Test
	void contextLoads() {
	}

	@Test
	void testMessage() {
		String message=messageController.getMessage();
		Assertions.assertNotNull(message);
	}
}
