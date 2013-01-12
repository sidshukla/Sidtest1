package com.cloudreach.solution.metadata;

import java.util.List;
import java.util.Map;

import com.cloudreach.solution.model.Transaction;

public class TransactionMetadata {
	
	Map<String, Transaction> transactionMap = null;
	List<Transaction> sortedTransactionOnSold = null;

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

	public void calculateMetadata(){
		
		
		
	}
}
