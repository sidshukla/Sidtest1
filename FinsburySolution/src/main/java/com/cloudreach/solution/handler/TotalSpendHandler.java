package com.cloudreach.solution.handler;

import java.util.Map;

import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;

/*
 *  Assuming if you sold n of a certain product where n is a multiple of the batchsize,
 *  we are assuming that you had that exact multiple and not more
 */
public class TotalSpendHandler {

	public Double processRequest(TransactionMetadata transactionMetadata , StockItemMetadata stockItemMetadata) {
		Double totalSpend = 0.0;
		
		Map<String , Transaction> transasctionMap = transactionMetadata.getTransactionMap();
		Map<String , StockItem> stockItemMap = stockItemMetadata.getStockItemEAMMapping();
		
		for(String eam :  stockItemMetadata.getStockItemEAMMapping().keySet()){
			int numberOfBatches = 0;
			int quantitySold = 0;
			if(transasctionMap.get(eam) == null){
				quantitySold =  stockItemMap.get(eam).getBatchSize() + 1;
			}else{
				quantitySold = transasctionMap.get(eam).getQuantity();
			}
			
			
			if(quantitySold % stockItemMap.get(eam).getBatchSize()==0){
				numberOfBatches= quantitySold/stockItemMap.get(eam).getBatchSize(); 
			}else{
				Integer divider= quantitySold/stockItemMap.get(eam).getBatchSize();
				numberOfBatches=divider+1;
			}
			totalSpend = totalSpend + (numberOfBatches*stockItemMap.get(eam).getBatchSize() * stockItemMap.get(eam).getWholesalePrice());
		}
		return totalSpend;
	}

}
