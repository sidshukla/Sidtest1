package com.cloudreach.solution.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudreach.solution.model.StockItem;
/*
 * Metadata from the WSSRF file converted into the stock item model
 * 
 * @Author : Siddharth
 */
public class StockItemMetadata {
	
	/*
	 * List of all the stock items 
	 */
	List<StockItem> stockItems ;
	
	/*
	 * Map of all stock items with the EAM number as key 
	 */
	Map<String , StockItem> stockItemEAMMapping; 
	
	public StockItemMetadata() {
		this.stockItemEAMMapping = new HashMap<String, StockItem>();
	}
	
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


	/*
	 * Calculate the metadata information
	 */
	public void calculateMetadata(List<StockItem> stockItems){
		this.stockItems = stockItems;
		calculateStockItemEAMMapping();
		
	}

	private void calculateStockItemEAMMapping() {
		for(StockItem stockItem : this.stockItems){
			this.stockItemEAMMapping.put(stockItem.getEan(), stockItem);
		}
	}
}
