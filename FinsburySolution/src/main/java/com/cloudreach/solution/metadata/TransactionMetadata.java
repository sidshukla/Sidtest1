package com.cloudreach.solution.metadata;

import java.util.List;
import java.util.Map;

import com.cloudreach.solution.model.Brand;
import com.cloudreach.solution.model.Transaction;

public class TransactionMetadata {
	
	private Map<String, Transaction> transactionMap = null;
	private List<Transaction> sortedTransactionOnSold = null;
	private List<Brand> sortedBrandQuantitySoldList = null;
	private Double totalProfit = 0.0;

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

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}


}
