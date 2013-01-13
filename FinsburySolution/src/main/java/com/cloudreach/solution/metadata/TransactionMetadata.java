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

public class TransactionMetadata {
	
	private Map<String, Transaction> transactionMap;
	private List<Transaction> sortedTransactionOnSold;
	private List<Brand> sortedBrandQuantitySoldList;
	private List<Product> sortedProfitProducts;
	
	public TransactionMetadata() {
		sortedTransactionOnSold = new ArrayList<Transaction>();
		sortedBrandQuantitySoldList = new ArrayList<Brand>();
		setSortedProfitProducts(new ArrayList<Product>());
	}

	public void calculateMetadata(Map<String, Transaction> transactionMap , Map<String, StockItem> stockItemMap) {
		this.setTransactionMap(transactionMap);

		Map<String , Brand> brandQuantitySoldTempMap = new HashMap<String, Brand>();
		Map<String , Product> productTempMap = new HashMap<String, Product>();
		
		for(String eam : transactionMap.keySet()){
			String brandName = stockItemMap.get(eam).getBrandName();
			
			Transaction transaction = transactionMap.get(eam);
			transaction.setBrandName(brandName);
			transaction.setProductName(stockItemMap.get(eam).getProductName());
			
			if(brandQuantitySoldTempMap.containsKey(brandName)){
				Brand localBrandName = brandQuantitySoldTempMap.get(brandName);
				localBrandName.setQuantity(localBrandName.getQuantity() + transaction.getQuantity());
			}else{
				Brand brand = new Brand();
				brand.setBrand(brandName);
				brand.setQuantity(transaction.getQuantity());
				brandQuantitySoldTempMap.put(brandName, brand);
			}
			
			if(productTempMap.containsKey(eam)){
				Product localProduct = productTempMap.get(eam);
				Double profit = transaction.getQuantity() * (stockItemMap.get(eam).getSellingPrice() - stockItemMap.get(eam).getWholesalePrice());
				localProduct.setProfit(localProduct.getProfit() + profit);
			}else{
				Product product = new Product();
				product.setEam(eam);
				product.setProductName(stockItemMap.get(eam).getProductName());
				product.setProfit(transaction.getQuantity() * (stockItemMap.get(eam).getSellingPrice() - stockItemMap.get(eam).getWholesalePrice()));
				productTempMap.put(eam, product);
			}
		}
		calculateSortedTransactionList(transactionMap);
		calculateSortedBrandsList(brandQuantitySoldTempMap);
		calculateSortedProfitProductList(productTempMap);
	}

	private void calculateSortedProfitProductList(Map<String, Product> productTempMap) {
		List<Product> sortedProfitProducts = new ArrayList<Product>(productTempMap.values());
		Collections.sort(sortedProfitProducts);
		this.setSortedProfitProducts(sortedProfitProducts);
	}

	private void calculateSortedTransactionList(Map<String, Transaction> transactionsMap) {
		List<Transaction> transactions = new ArrayList<Transaction>(transactionsMap.values());
		Collections.sort(transactions);
		this.setSortedTransactionOnSold(transactions);
	}
	
	private void calculateSortedBrandsList(Map<String, Brand> brandQuantitySoldTempMap){
		List<Brand> sortedBrands = new ArrayList<Brand>(brandQuantitySoldTempMap.values());
		Collections.sort(sortedBrands);
		this.setSortedBrandQuantitySoldList(sortedBrands);
	}
	
	public List<Transaction> getSortedTransactionOnSold() {
		return sortedTransactionOnSold;
	}

	public void setSortedTransactionOnSold(List<Transaction> sortedTransactionOnSold) {
		this.sortedTransactionOnSold = sortedTransactionOnSold;
	}

	public Map<String, Transaction> getTransactionMap() {
		return transactionMap;
	}

	public void setTransactionMap(Map<String, Transaction> transactionMap) {
		this.transactionMap = transactionMap;
	}

	public List<Brand> getSortedBrandQuantitySoldList() {
		return sortedBrandQuantitySoldList;
	}

	public void setSortedBrandQuantitySoldList(
			List<Brand> sortedBrandQuantitySoldMap) {
		this.sortedBrandQuantitySoldList = sortedBrandQuantitySoldMap;
	}

	public List<Product> getSortedProfitProducts() {
		return sortedProfitProducts;
	}

	public void setSortedProfitProducts(List<Product> sortedProfitProducts) {
		this.sortedProfitProducts = sortedProfitProducts;
	}
}
