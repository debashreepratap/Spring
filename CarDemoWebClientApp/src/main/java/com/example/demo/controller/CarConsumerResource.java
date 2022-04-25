package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Car;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CarConsumerResource {
	@Autowired
	WebClient webClient;
	
	Logger log = LoggerFactory.getLogger(CarConsumerResource.class);
	
	
	
	@PostMapping("/create")
	public Mono<String> carCreation(@RequestBody Car car){
		log.info("webClient.post().uri(\"/cars\")"+webClient.post().uri("/cars"));
		return webClient.post().uri("/cars").syncBody(car).retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("/getAll")
	public Flux<Car> getAllCars(){
		
		return webClient.get().uri("/cars").retrieve().bodyToFlux(Car.class);
	}
	
	@GetMapping("/get/{carId}")
	public Mono<Car> getCarById(@PathVariable int carId){
		return webClient.get().uri("/car/"+carId).retrieve().bodyToMono(Car.class);
	}
	
	@DeleteMapping("/delete/{carId}")
	public Mono<String> deleteCar(@PathVariable int carId){
		return webClient.delete().uri("/car/"+carId).retrieve().bodyToMono(String.class);
	}
}
