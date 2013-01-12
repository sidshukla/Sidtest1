package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.List;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

public class BottomBrandBySoldHandler implements RequestHandler {

	@Override
	public List<String> processRequest(StockItemMetadata stockItemMetadata,
			TransactionMetadata transactionMetadata, int requestSize) {
		
		List<String> topBrands = new ArrayList<String>();
		for (int i = transactionMetadata.getSortedBrandQuantitySoldList().size(); i > transactionMetadata
				.getSortedBrandQuantitySoldList().size() - requestSize; i--) {
			topBrands.add(transactionMetadata.getSortedBrandQuantitySoldList().get(i-1).getBrand());
		}

		return topBrands;
	}

}
