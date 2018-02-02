package com.example.messageprocessor.util;

import java.util.HashMap;
import java.util.List;

import com.example.messageprocessor.main.MessageProcessorConstants;
import com.example.messageprocessor.vo.AdjustedProduct;
import com.example.messageprocessor.vo.ConsolidatedProduct;

/**
 * @author Nikhil Manjrekar
 * 
 *         Util class to provide reporting methods.
 *
 */
public class ReportsUtil {

	/**
	 * @param productDetails
	 * 
	 *            This method generates a sales report after every 10th sales
	 *            record processed.
	 */
	public void generateSalesReport(HashMap<String, ConsolidatedProduct> productDetails) {
		System.out.println("Application has processed 10 Sales Records.");
		System.out.println("|--------------------------|");
		System.out.println("|       SALES REPORT       |");
		System.out.println("|--------------------------|");
		System.out.println(String.format(MessageProcessorConstants.SALES_REPORT_HEADER_FORMAT,
				MessageProcessorConstants.PRODUCT, MessageProcessorConstants.QTY, MessageProcessorConstants.PRICE));
		System.out.println("|..........................|");
		double totalSale = 0;
		for (String key : productDetails.keySet()) {
			totalSale = totalSale + productDetails.get(key).getTotalPrice();
			System.out.println(String.format(MessageProcessorConstants.SALES_REPORT_FORMAT, key,
					productDetails.get(key).getTotalQuantity(), productDetails.get(key).getTotalPrice()));
		}
		System.out.println("|--------------------------|");
		System.out.println("| Total sales : "
				+ String.format(MessageProcessorConstants.DOUBLE_TWO_DECIMAL_PRECISION_PATTERN, totalSale) + "      |");
		System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxx|");
		System.out.println("\n");
	}

	/**
	 * @param adjustedProductsList
	 * 
	 *            This method generates adjustment report after processing 50
	 *            sales records
	 */
	public void generateAdjustmentReport(List<AdjustedProduct> adjustedProductsList) {
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|                           ADJUSTMENT REPORT                              |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println(String.format(MessageProcessorConstants.ADJUSTMENT_REPORT_HEADER_FORMAT,
				MessageProcessorConstants.PRODUCT, MessageProcessorConstants.OPERATION_HEADER,
				MessageProcessorConstants.ADJUSTMENT_FACTOR, MessageProcessorConstants.QTY,
				MessageProcessorConstants.PRICE_BEFORE_ADJUSTMENT, MessageProcessorConstants.PRICE_AFTER_ADJUSTMENT));
		System.out.println("|--------------------------------------------------------------------------|");
		for (AdjustedProduct adjustedProduct : adjustedProductsList) {
			System.out.println(String.format(MessageProcessorConstants.ADJUSTMENT_REPORT_FORMAT,
					adjustedProduct.getAdjustmentProductType(), adjustedProduct.getAdjustmentOperation(),
					adjustedProduct.getAdjustmentFactor(), adjustedProduct.getAdjustmentQuantity(),
					adjustedProduct.getPriceBeforeAdjustment(), adjustedProduct.getPriceAfterAdjustment()));
		}
		System.out.println("|---------------------------------- END -----------------------------------|");
	}
}
