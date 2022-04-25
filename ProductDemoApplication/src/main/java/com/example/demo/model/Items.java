package com.example.demo.model;

import java.util.List;

public class Items{

	/**
	 * 
	 */
	private List<Product> products;
	private String total;
	private String skip;
	private String limit;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	
}
