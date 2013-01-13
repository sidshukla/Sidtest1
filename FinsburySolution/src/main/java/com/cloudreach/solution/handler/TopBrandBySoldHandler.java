package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.List;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;

/*
 * Handler class for getting the Top brands by number sold
 * 
 * returns a list of size : requestSize
 */
public class TopBrandBySoldHandler implements RequestHandler {

	@Override
	public List<String> processRequest(StockItemMetadata stockItemMetadata,
			TransactionMetadata transactionMetadata, int requestSize) {
		
		List<String> bottomBrands = new ArrayList<String>();
		for (int i = 0; i < requestSize; i++) {
			bottomBrands.add(transactionMetadata.getSortedBrandQuantitySoldList().get(i).getBrand());
		}

		return bottomBrands;
	}

}
