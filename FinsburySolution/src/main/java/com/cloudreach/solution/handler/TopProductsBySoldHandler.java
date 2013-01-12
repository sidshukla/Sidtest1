package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.List;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

public class TopProductsBySoldHandler implements RequestHandler {

	@Override
	public List<String> processRequest(StockItemMetadata stockItemMetadata,
			TransactionMetadata transactionMetadata, int requestSize) {
		
		List<String> topProducts = new ArrayList<String>();
		
		for(int i=0 ; i< requestSize ; i++){
			topProducts.add(transactionMetadata.getSortedTransactionOnSold().get(i).getProductName());
		}
		
		return topProducts;
	}


}
