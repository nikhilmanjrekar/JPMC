package com.example.messageprocessor.vo;

/**
 * @author Nikhil Manjrekar
 *
 */
public class ConsolidatedProduct {

	/**
	 * variable to hold product type
	 */
	private String type;
	/**
	 * variable to hold total quantity
	 */
	private int totalQuantity;
	/**
	 * variable to hold total price
	 */
	private double totalPrice;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
