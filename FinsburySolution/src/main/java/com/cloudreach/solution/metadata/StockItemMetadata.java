package com.cloudreach.solution.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudreach.solution.model.StockItem;

public class StockItemMetadata {
	
	List<StockItem> stockItems ;
	Map<String , StockItem> stockItemEAMMapping; 
	
	public Map<String, StockItem> getStockItemEAMMapping() {
		return stockItemEAMMapping;
	}

	public void setStockItemEAMMapping(Map<String, StockItem> stockItemEAMMapping) {
		this.stockItemEAMMapping = stockItemEAMMapping;
	}

	public void setStockItems(List<StockItem> stockItems){
		this.stockItems = stockItems;
	}
	
	public List<StockItem> getStockItems(){
		return this.stockItems;
	}
	
	public void calculateMetadata(List<StockItem> stockItems){
		this.stockItems = stockItems;
		this.stockItemEAMMapping = new HashMap<String, StockItem>();
		calculateStockItemEAMMapping();
		
	}

	private void calculateStockItemEAMMapping() {
		for(StockItem stockItem : this.stockItems){
			this.stockItemEAMMapping.put(stockItem.getEan(), stockItem);
		}
	}
}
