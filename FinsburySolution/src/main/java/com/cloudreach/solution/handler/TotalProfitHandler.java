package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

/*
 *  Assuming if you sold n number of a certain product and its a multiple of the batch size,
 *  we are assuming that you had ordered exact multiple of batches
 */
public class TotalProfitHandler{

	public Double processRequest(TransactionMetadata transactionMetadata , StockItemMetadata stockItemMetadata) {
		return transactionMetadata.getTotalEarned() - transactionMetadata.getTotalSpend();
	}

}
