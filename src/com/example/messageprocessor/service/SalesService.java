package com.example.messageprocessor.service;

/**
 * @author Nikhil Manjrekar
 *
 *         Interface for service level implementations
 */
public interface SalesService {

	public boolean processSalesRecord(String saleRecord);

	public void printAdjustmentReport();
}
