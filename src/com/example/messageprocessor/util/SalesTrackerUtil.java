package com.example.messageprocessor.util;

import com.example.messageprocessor.main.MessageProcessorConstants;
import com.example.messageprocessor.vo.AdjustedProduct;
import com.example.messageprocessor.vo.ConsolidatedProduct;
import com.example.messageprocessor.vo.Product;

/**
 * @author Nikhil Manjrekar
 *
 *         A util class to provide sales tracking methods.
 */
public class SalesTrackerUtil {

	/**
	 * @param consolidatedProduct
	 * @param product
	 * @return ConsolidatedProduct
	 * 
	 *         This method works on the consolidatedProduct object from Hashmap,
	 *         performs the adjustment based on the operation in the salesRecord
	 *         and returns the updated consolidatedProduct to be saved to
	 *         Hashmap.
	 */
	public ConsolidatedProduct performAdjustment(ConsolidatedProduct consolidatedProduct, Product product) {
		double adjustment;
		adjustment = consolidatedProduct.getTotalQuantity() * product.getPrice();

		if ((MessageProcessorConstants.ADD).equalsIgnoreCase(product.getOperation())) {
			consolidatedProduct.setTotalPrice(consolidatedProduct.getTotalPrice() + adjustment);
		} else if ((MessageProcessorConstants.SUBTRACT).equalsIgnoreCase(product.getOperation())) {
			consolidatedProduct.setTotalPrice(consolidatedProduct.getTotalPrice() - adjustment);
		} else if ((MessageProcessorConstants.MULTIPLY).equalsIgnoreCase(product.getOperation())) {
			consolidatedProduct.setTotalPrice(consolidatedProduct.getTotalPrice() * adjustment);
		}

		return consolidatedProduct;
	}

	/**
	 * @param consolidatedProduct
	 * @param product
	 * @return AdjustedProduct
	 * 
	 *         This method builds the adjustedProduct with data to be used for
	 *         Adjustment Report
	 */
	public AdjustedProduct createAdjustedProduct(ConsolidatedProduct consolidatedProduct, Product product) {
		AdjustedProduct adjustedProduct = new AdjustedProduct();

		adjustedProduct.setAdjustmentOperation(product.getOperation());
		adjustedProduct.setAdjustmentFactor(product.getPrice());
		adjustedProduct.setAdjustmentProductType(product.getType());
		adjustedProduct.setAdjustmentQuantity(consolidatedProduct.getTotalQuantity());
		adjustedProduct.setPriceAfterAdjustment(consolidatedProduct.getTotalPrice());

		return adjustedProduct;
	}
}
