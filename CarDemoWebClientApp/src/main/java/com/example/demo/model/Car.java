package com.example.demo.model;

public class Car {
	
    private int id;
 
	private String brand;
	
	private String version;
	
	private double price;
 
    private String fuelType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", version=" + version + ", price=" + price + ", fuelType="
				+ fuelType + "]";
	}
    
    
 
}
