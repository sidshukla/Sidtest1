package com.cloudreach.solution.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudreach.solution.model.Brand;
import com.cloudreach.solution.model.Product;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;

/*
 * Metadata from the WSSRF file and FFTRFP file
 * converted into the model classes and maps
 * 
 * @Author : Siddharth
 */
public class TransactionMetadata {
	
	/*
	 * Map containing all the transactions with the EAM number as key
	 */
	private Map<String, Transaction> transactionMap;
	
	/*
	 * List of all the transactions sorted on the quantity sold
	 */
	private List<Transaction> sortedTransactionOnSold;
	
	/*
	 * List of all the brans sorted on the quantity sold
	 */
	private List<Brand> sortedBrandQuantitySoldList;
	
	/*
	 * List of all the products sorted on Profit
	 */
	private List<Product> sortedProfitProducts;
	
	/*
	 * Total spend value
	 */
	private Double totalSpend;
	
	/*
	 * Total earned value
	 */
	private Double totalEarned;
	
	public TransactionMetadata() {
		sortedTransactionOnSold = new ArrayList<Transaction>();
		sortedBrandQuantitySoldList = new ArrayList<Brand>();
		sortedProfitProducts = new ArrayList<Product>();
		totalSpend = new Double(0.0);
		totalEarned = new Double(0.0);
	}

	public void calculateMetadata(Map<String, Transaction> transactionMap , Map<String, StockItem> stockItemMap) {
		this.transactionMap = transactionMap;

		Map<String , Brand> brandQuantitySoldTempMap = new HashMap<String, Brand>();
		List<Product> productsByProfitTempList = new ArrayList<Product>();
		
		for(String eam : transactionMap.keySet()){
			String brandName = stockItemMap.get(eam).getBrandName();
			
			Transaction transaction = transactionMap.get(eam);
			transaction.setBrandName(brandName);
			transaction.setProductName(stockItemMap.get(eam).getProductName());
			
			/*
			 * Consolidating all the brands and the quantity sold
			 */
			if(brandQuantitySoldTempMap.containsKey(brandName)){
				Brand localBrandName = brandQuantitySoldTempMap.get(brandName);
				localBrandName.setQuantity(localBrandName.getQuantity() + transaction.getQuantity());
			}else{
				Brand brand = new Brand();
				brand.setBrand(brandName);
				brand.setQuantity(transaction.getQuantity());
				brandQuantitySoldTempMap.put(brandName, brand);
			}
			
			/*
			 * Calculating profit on each product
			 */
			Double totalEarnedByProduct = transaction.getQuantity() * stockItemMap.get(eam).getSellingPrice();
			int batchSize = calculateBatchSize(transactionMap, stockItemMap, eam);
			Double totalSpendForProduct = batchSize*stockItemMap.get(eam).getBatchSize()
												   *stockItemMap.get(eam).getWholesalePrice();
			
			Product product = new Product();
			product.setEam(eam);
			product.setProductName(stockItemMap.get(eam).getProductName());
			product.setTotalProfit(totalEarnedByProduct - totalSpendForProduct);
			
			productsByProfitTempList.add(product);
			totalSpend = totalSpend + totalSpendForProduct;
			totalEarned = totalEarned + totalEarnedByProduct;
			
		}
		calculateSortedTransactionList(transactionMap);
		calculateSortedBrandsList(brandQuantitySoldTempMap);
		calculateSortedProfitProductList(productsByProfitTempList);
	}
	
	private void calculateSortedProfitProductList(List<Product> productsByProfitTempList) {
		Collections.sort(productsByProfitTempList);
		this.sortedProfitProducts = productsByProfitTempList;
	}

	private void calculateSortedTransactionList(Map<String, Transaction> transactionsMap) {
		List<Transaction> transactions = new ArrayList<Transaction>(transactionsMap.values());
		Collections.sort(transactions);
		this.sortedTransactionOnSold = transactions;
	}
	
	private void calculateSortedBrandsList(Map<String, Brand> brandQuantitySoldTempMap){
		List<Brand> sortedBrands = new ArrayList<Brand>(brandQuantitySoldTempMap.values());
		Collections.sort(sortedBrands);
		this.sortedBrandQuantitySoldList = sortedBrands;
	}
	
	/*
	 *  Assuming if you sold n of a certain product where n is a multiple of the batchsize,
	 *  we are assuming that you had that exact multiple and not more
	 */
	private int calculateBatchSize(Map<String, Transaction> transactionMap , Map<String, StockItem> stockItemMap , String eam){
		int numberOfBatches = 0;
		int quantitySold = 0;
		/*
		 * If the item is not sold at all , we assume you have ordered only 1 batch
		 */
		if (transactionMap.get(eam) == null) {
			numberOfBatches = 1;
		}else{
			quantitySold = transactionMap.get(eam).getQuantity();
		}
		if (numberOfBatches == 0) {
			if (quantitySold % stockItemMap.get(eam).getBatchSize() == 0) {
				numberOfBatches = quantitySold
						/ stockItemMap.get(eam).getBatchSize();
			} else {
				Integer divider = quantitySold
						/ stockItemMap.get(eam).getBatchSize();
				numberOfBatches = divider + 1;
			}
		}
		
		return numberOfBatches;
	}
	
	public List<Transaction> getSortedTransactionOnSold() {
		return sortedTransactionOnSold;
	}

	public Map<String, Transaction> getTransactionMap() {
		return transactionMap;
	}
	
	public List<Brand> getSortedBrandQuantitySoldList() {
		return sortedBrandQuantitySoldList;
	}

	public List<Product> getSortedProfitProducts() {
		return sortedProfitProducts;
	}

	public Double getTotalSpend() {
		return totalSpend;
	}

	public Double getTotalEarned() {
		return totalEarned;
	}
}
