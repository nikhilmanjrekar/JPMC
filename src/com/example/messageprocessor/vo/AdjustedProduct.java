package com.example.messageprocessor.vo;

/**
 * @author Nikhil Manjrekar
 *
 */
public class AdjustedProduct {

	/**
	 * variable to hold adjustment cost
	 */
	private double adjustmentFactor;
	/**
	 * variable to hold adjustment operation
	 */
	private String adjustmentOperation;
	/**
	 * variable to hold adjustment product
	 */
	private String adjustmentProductType;
	/**
	 * variable to hold adjustment product quantity
	 */
	private int adjustmentQuantity;
	/**
	 * variable to hold price before adjustment
	 */
	private double priceBeforeAdjustment;
	/**
	 * variable to hold price after adjustment
	 */
	private double priceAfterAdjustment;
	
	
	public String getAdjustmentProductType() {
		return adjustmentProductType;
	}
	public void setAdjustmentProductType(String adjustmentProductType) {
		this.adjustmentProductType = adjustmentProductType;
	}
	
	public double getAdjustmentFactor() {
		return adjustmentFactor;
	}
	public void setAdjustmentFactor(double adjustmentFactor) {
		this.adjustmentFactor = adjustmentFactor;
	}
	
	public String getAdjustmentOperation() {
		return adjustmentOperation;
	}
	public void setAdjustmentOperation(String adjustmentOperation) {
		this.adjustmentOperation = adjustmentOperation;
	}
	
	public int getAdjustmentQuantity() {
		return adjustmentQuantity;
	}
	public void setAdjustmentQuantity(int adjustmentQuantity) {
		this.adjustmentQuantity = adjustmentQuantity;
	}
	
	public double getPriceBeforeAdjustment() {
		return priceBeforeAdjustment;
	}
	public void setPriceBeforeAdjustment(double priceBeforeAdjustment) {
		this.priceBeforeAdjustment = priceBeforeAdjustment;
	}
	
	public double getPriceAfterAdjustment() {
		return priceAfterAdjustment;
	}
	public void setPriceAfterAdjustment(double priceAfterAdjustment) {
		this.priceAfterAdjustment = priceAfterAdjustment;
	}	
}
