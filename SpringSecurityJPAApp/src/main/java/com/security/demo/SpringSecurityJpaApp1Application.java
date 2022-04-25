package com.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityJpaApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApp1Application.class, args);
	}

}
