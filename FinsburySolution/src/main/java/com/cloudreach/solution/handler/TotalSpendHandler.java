package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

/*
 * Handler class for getting the Total spend
 * 
 * returns a list of size : requestSize
 */
public class TotalSpendHandler {

	public Double processRequest(TransactionMetadata transactionMetadata,
			StockItemMetadata stockItemMetadata) {
		return transactionMetadata.getTotalSpend();
	}

}
