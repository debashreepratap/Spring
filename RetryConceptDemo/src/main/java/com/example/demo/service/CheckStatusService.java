package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class CheckStatusService {
	
	@Retryable(value = RuntimeException.class,maxAttempts = 3,backoff = @Backoff(3000),exclude=HttpClientErrorException.class)
   public String checkStatus(String trackingNumber) {
		System.out.println("Calling another service to get the status");
		throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
	   //return "approved";
   }
	@Recover
	public String recover() {
		return "Please try again after sometime";
	}
}
