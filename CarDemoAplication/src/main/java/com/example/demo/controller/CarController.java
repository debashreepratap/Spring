package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;

@RestController
@RequestMapping
public class CarController {
	
	@Autowired
	CarService carService;
	
	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getAllCars(){
		List<Car> cars = carService.getAllCars();
		if(!cars.isEmpty()) {
		  List<Car> carList=carService.getAllCars();
		  return new ResponseEntity<>(carList, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException("No Cars Found");
		}
		
	}
	
	@GetMapping("/car/{id}")
	  public ResponseEntity<Car> getCarById(@PathVariable("id") int id) {
	    Optional<Car> car = carService.findById(id);
	    if (car.isPresent()) {
	      return new ResponseEntity<>(car.get(), HttpStatus.OK);
	    } else {
	    	throw new ResourceNotFoundException("Car Not Found with id = " + id);
	    }
	  }

	 @PostMapping("/cars")
	  public ResponseEntity<Car> createCarModel(@RequestBody Car car) {
	    try {
	    	Car car1= carService.createCar(car);
	      return new ResponseEntity<>(car1, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @DeleteMapping("/car/{id}")
	  public ResponseEntity<Car> deleteCar(@PathVariable("id") int id) {
		 Optional<Car> car = carService.findById(id);
		    if (car.isPresent()) {
	    	carService.deleteById(id);
	    	 return new ResponseEntity<>(car.get(), HttpStatus.OK);
		    }
		    else
		    	throw new ResourceNotFoundException("Car Not Found with id = " + id);
	    
	  }
	 @PutMapping("/cars/{id}")
	  public ResponseEntity<Car> updateCarModel(@PathVariable("id") int id, @RequestBody Car car) {
	    Optional<Car> carData = carService.findById(id);
	    if (carData.isPresent()) {
	    	Car newCar = carData.get();
	    	newCar.setBrand(car.getBrand());
	    	newCar.setVersion(car.getVersion());
	    	newCar.setPrice(car.getPrice());
	    	newCar.setFuelType(car.getFuelType());
	      return new ResponseEntity<>(carService.createCar(newCar), HttpStatus.OK);
	    } else {
	    	throw new ResourceNotFoundException("Car Not Found with id = " + id);
	    }
	  }
}
