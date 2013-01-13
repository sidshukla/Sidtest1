package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

/*
 * Handler class for getting the total profit
 * 
 * returns a list of size : requestSize
 */
public class TotalProfitHandler{

	public Double processRequest(TransactionMetadata transactionMetadata , StockItemMetadata stockItemMetadata) {
		return transactionMetadata.getTotalEarned() - transactionMetadata.getTotalSpend();
	}

}
