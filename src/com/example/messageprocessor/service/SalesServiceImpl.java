package com.example.messageprocessor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.messageprocessor.util.RecordParserUtil;
import com.example.messageprocessor.util.ReportsUtil;
import com.example.messageprocessor.util.SalesTrackerUtil;
import com.example.messageprocessor.vo.AdjustedProduct;
import com.example.messageprocessor.vo.ConsolidatedProduct;
import com.example.messageprocessor.vo.Product;

/**
 * @author Nikhil Manjrekar
 *
 *         Service class to process each sales record and to show reports
 */
public class SalesServiceImpl implements SalesService {

	private HashMap<String, ConsolidatedProduct> productDetails = new HashMap<>();
	List<AdjustedProduct> adjustedProductsList = new ArrayList<>();
	ReportsUtil reportsUtil = null;
	int recordCount = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.messageprocessor.service.SalesService#processSalesRecord(java
	 * .lang.String)
	 */
	@Override
	public boolean processSalesRecord(String saleRecord) {

		ConsolidatedProduct consolidatedProduct = null;

		// Parsing each record to create a product object
		RecordParserUtil recordParserUtil = new RecordParserUtil();
		Product product = recordParserUtil.parseSalesRecord(saleRecord);

		if (null != product) {
			SalesTrackerUtil salesTrackerUtil = new SalesTrackerUtil();
			reportsUtil = new ReportsUtil();

			recordCount = recordCount + 1;
			consolidatedProduct = productDetails.getOrDefault(product.getType(), new ConsolidatedProduct());

			if (null != product.getOperation()) {
				double priceBeforeAdjustment = consolidatedProduct.getTotalPrice();

				// Calculate adjustment to be performed and populate the
				// consolidatedProduct.
				consolidatedProduct = salesTrackerUtil.performAdjustment(consolidatedProduct, product);

				// Create adjustedProduct and add it to list to show the
				// Adjustment Report
				AdjustedProduct adjustedProduct = salesTrackerUtil.createAdjustedProduct(consolidatedProduct, product);
				adjustedProduct.setPriceBeforeAdjustment(priceBeforeAdjustment);
				adjustedProductsList.add(adjustedProduct);
			} else {
				// Normal sales record without adjustment
				consolidatedProduct.setType(product.getType());
				consolidatedProduct.setTotalPrice(
						consolidatedProduct.getTotalPrice() + (product.getPrice() * product.getQuantity()));
				consolidatedProduct.setTotalQuantity(consolidatedProduct.getTotalQuantity() + product.getQuantity());
			}

			// Add consolidatedProduct to the hashmap based on product type for
			// reporting
			productDetails.put(product.getType(), consolidatedProduct);

			// Print a report after every 10th sales record processed
			if (recordCount % 10 == 0) {
				reportsUtil.generateSalesReport(productDetails);
				// Pausing for a while so that Sales report generation can be
				// seen for each set of 10 products
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// After processing 50 records, stop processing further and print
			// Adjustment report
			if (recordCount == 50) {
				return false;
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.messageprocessor.service.SalesService#printAdjustmentReport()
	 * 
	 * Delegates adjustment report printing task to reportsUtil
	 */
	public void printAdjustmentReport() {
		reportsUtil = new ReportsUtil();
		reportsUtil.generateAdjustmentReport(adjustedProductsList);
	}
}
