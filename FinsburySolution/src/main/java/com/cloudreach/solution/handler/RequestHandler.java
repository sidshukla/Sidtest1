package com.cloudreach.solution.handler;

import java.util.List;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

public interface RequestHandler {
	
	List<String> processRequest(StockItemMetadata stockItemMetadata  , TransactionMetadata transactionMetadata , int requestSize);
	
}
