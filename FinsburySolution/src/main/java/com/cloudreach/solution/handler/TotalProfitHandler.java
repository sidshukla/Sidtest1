package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.TransactionMetadata;

public class TotalProfitHandler{

	public Double processRequest(TransactionMetadata transactionMetadata) {
		return transactionMetadata.getTotalProfit();
	}

}
