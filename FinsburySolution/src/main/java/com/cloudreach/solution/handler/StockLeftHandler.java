package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.List;

import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.Product;

public class StockLeftHandler {
	
	public List<String> processRequest(TransactionMetadata transactionMetadata){
		
		List<String> stockLeftList = new ArrayList<String>();
		for(Product product : transactionMetadata.getSortedProfitProducts()){
			StringBuffer output = new StringBuffer();
			output.append("Quantity of stock left for product ")
					.append(product.getProductName()).append(" is : ")
					.append(product.getStockLeft());
			
			stockLeftList.add(output.toString());
		}
		return stockLeftList;
	}

}
