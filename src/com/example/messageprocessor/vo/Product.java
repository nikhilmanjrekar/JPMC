package com.example.messageprocessor.vo;

/**
 * @author Nikhil Manjrekar
 *
 */
public class Product {

	/**
	 * variable to hold product type
	 */
	private String type;
	/**
	 * variable to hold product price
	 */
	private double price;
	/**
	 * variable to hold product quantity
	 */
	private int quantity;
	/**
	 * variable to hold product operation
	 */
	private String operation;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}	
	
}
