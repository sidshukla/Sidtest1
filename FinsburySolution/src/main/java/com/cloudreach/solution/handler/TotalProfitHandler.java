package com.cloudreach.solution.handler;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;

public class TotalProfitHandler{

	public Double processRequest(TransactionMetadata transactionMetadata , StockItemMetadata stockItemMetadata) {
		Double profit = 0.0;
		for(String eam :  transactionMetadata.getTransactionMap().keySet()){
			int quantitySold = transactionMetadata.getTransactionMap().get(eam).getQuantity();
			StockItem stockItem = stockItemMetadata.getStockItemEAMMapping().get(eam);
			profit = profit + quantitySold*(stockItem.getSellingPrice() - stockItem.getWholesalePrice());
		}
		return profit;
	}

}
