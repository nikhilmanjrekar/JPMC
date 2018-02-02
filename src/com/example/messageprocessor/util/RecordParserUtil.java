package com.example.messageprocessor.util;

import com.example.messageprocessor.main.MessageProcessorConstants;
import com.example.messageprocessor.vo.Product;

/**
 * @author Nikhil Manjrekar
 *
 *         Utility class to parse records from input
 */
public class RecordParserUtil {

	/**
	 * @param saleRecord
	 * @return Product
	 * 
	 *         Processes each sales record and identifies the type of message.
	 */
	public Product parseSalesRecord(String saleRecord) {
		if (saleRecord == null || saleRecord.isEmpty()) {
			return null;
		}
		String[] salesRecordArray = saleRecord.trim().split(MessageProcessorConstants.SALES_RECORD_SPLIT_PATTERN);
		String firstWord = salesRecordArray[0];

		if (salesRecordArray.length == 3 && salesRecordArray[1].contains(MessageProcessorConstants.AT)) {
			return parseSalesRecordFormat1(salesRecordArray);
		} else if (salesRecordArray.length == 7 && firstWord.matches(MessageProcessorConstants.NUMBER_MATCH_PATTERN)) {
			return parseSalesRecordFormat2(salesRecordArray);
		} else if (salesRecordArray.length == 3 && firstWord.matches(MessageProcessorConstants.OPERATION)) {
			return parseSalesRecordFormat3(salesRecordArray);
		}

		return null;
	}

	/**
	 * @param messageArray
	 * @return Product
	 * 
	 *         Parsing logic for sales record format 1
	 */
	private Product parseSalesRecordFormat1(String[] messageArray) {
		Product parsedRecord = new Product();

		parsedRecord.setType(messageArray[0]);
		parsedRecord.setPrice(parsePrice(messageArray[2]));
		parsedRecord.setQuantity(1);
		parsedRecord.setOperation(null);

		return parsedRecord;
	}

	/**
	 * @param messageArray
	 * @return Product
	 * 
	 *         Parsing logic for sales record format 2
	 */
	private Product parseSalesRecordFormat2(String[] messageArray) {
		Product parsedRecord = new Product();

		parsedRecord.setType(messageArray[3]);
		parsedRecord.setPrice(parsePrice(messageArray[5]));
		parsedRecord.setQuantity(Integer.parseInt(messageArray[0]));
		parsedRecord.setOperation(null);

		return parsedRecord;
	}

	/**
	 * @param messageArray
	 * @return Product
	 * 
	 *         Parsing logic for sales record format 3
	 */
	private Product parseSalesRecordFormat3(String[] messageArray) {
		Product parsedRecord = new Product();

		parsedRecord.setType(messageArray[2]);
		parsedRecord.setQuantity(0);
		parsedRecord.setPrice(parsePrice(messageArray[1]));
		parsedRecord.setOperation(messageArray[0]);

		return parsedRecord;
	}

	/**
	 * @param pricePaise
	 * @return double
	 * 
	 *         price parser to remove currency and convert to bigger currency
	 *         (20p ---> 0.20)
	 */
	public double parsePrice(String pricePaise) {
		double priceRupee = Double.parseDouble(pricePaise.replaceAll(MessageProcessorConstants.REMOVE_CURRENCY_PATTERN,
				MessageProcessorConstants.EMPTY_STRING));
		if (!pricePaise.contains(MessageProcessorConstants.DOT)) {
			priceRupee = Double.valueOf(Double.valueOf(priceRupee) / Double.valueOf(MessageProcessorConstants.HUNDRED));
		}
		return priceRupee;
	}
}
