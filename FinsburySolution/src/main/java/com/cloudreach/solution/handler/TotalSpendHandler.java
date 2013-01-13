package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

/*
 *  Assuming if you sold n of a certain product where n is a multiple of the batchsize,
 *  we are assuming that you had that exact multiple and not more
 */
public class TotalSpendHandler {

	public Double processRequest(TransactionMetadata transactionMetadata,
			StockItemMetadata stockItemMetadata) {
		return transactionMetadata.getTotalSpend();
	}

}
