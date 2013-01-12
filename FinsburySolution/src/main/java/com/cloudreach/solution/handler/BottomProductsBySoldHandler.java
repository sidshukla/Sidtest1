package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.List;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

public class BottomProductsBySoldHandler implements RequestHandler {

	@Override
	public List<String> processRequest(StockItemMetadata stockItemMetadata,
			TransactionMetadata transactionMetadata, int requestSize) {
		
		List<String> bottomProducts = new ArrayList<String>();
		
		for(int i= transactionMetadata.getSortedTransactionOnSold().size()  ; i > transactionMetadata.getSortedTransactionOnSold().size() - requestSize ; i--){
			String barcode = transactionMetadata.getSortedTransactionOnSold().get(i-1).getEan();
			String productName = stockItemMetadata.getStockItemEAMMapping().get(barcode).getProductName();
			bottomProducts.add(productName);
		}
		
		return bottomProducts;
	}


}
