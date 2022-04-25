package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	CarRepository carRepository;

	public List<Car> getAllCars() {

		return carRepository.findAll();
		
	}

	public Optional<Car> findById(int id) {
		return carRepository.findById(id);
	}

	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	public void deleteById(int id) {
		 carRepository.deleteById(id);
		
		
	}

}
