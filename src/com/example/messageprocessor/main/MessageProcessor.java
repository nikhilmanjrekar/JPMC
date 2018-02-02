package com.example.messageprocessor.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.messageprocessor.service.SalesService;
import com.example.messageprocessor.service.SalesServiceImpl;

/**
 * @author Nikhil Manjrekar 
 * 
 * This class reads sales records from the input file
 * and delegates to service layer to perform processing and logging.
 */
public class MessageProcessor {
	public static void main(String[] args) {

		SalesService salesService = null;
		
		// Reading input sales records using try with resources feature
		try (FileInputStream inputStream = new FileInputStream("Input/SalesRecords.txt");
				Scanner scanner = new Scanner(inputStream, "UTF-8");) {
			salesService = new SalesServiceImpl();
			boolean continueFlag;
			
			while (scanner.hasNextLine()) {
				String record = scanner.nextLine();
				// Process each record from the input file
				continueFlag = salesService.processSalesRecord(record);
				if(!continueFlag){
					System.out.println("Application is now pausing as it has processed 50 records.");
					System.out.println("\n");
					break;
				}
			}
			
			// Print adjustment report
			salesService.printAdjustmentReport();
					
			// Since Scanner suppresses the exceptions
			if (scanner.ioException() != null) {
				throw scanner.ioException();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
