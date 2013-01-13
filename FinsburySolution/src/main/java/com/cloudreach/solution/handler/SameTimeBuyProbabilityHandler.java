package com.cloudreach.solution.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.Transaction;

/*
 * Handler class for getting the bottom brands by number sold
 * returns a list of size : requestSize
 * 
 * Assuming if a customer buys two products at the same time , the transaction id will be the same
 */
public class SameTimeBuyProbabilityHandler {
	
	public int processRequest(TransactionMetadata transactionMetadata){
		
		int totalTransactions = transactionMetadata.getSortedTransactionOnSold().size();
		int totalCustomerBuyingTwiceOrMore = 0;
		
		Map<Long , String> transactionIDEamMapping = new HashMap<Long ,String>(); 
		List<Long> ignoreTransactinIDList = new ArrayList<Long>();
		
		for(Transaction transaction : transactionMetadata.getSortedTransactionOnSold()){
			long transactionId  = transaction.getTransactionId();
			if (!ignoreTransactinIDList.contains(transactionId)
					&& transactionIDEamMapping.containsKey(transactionId) 
					&& transactionIDEamMapping.get(transactionId) != transaction.getEan()) {
					totalCustomerBuyingTwiceOrMore++;
					ignoreTransactinIDList.add(transaction.getTransactionId());
			}else{
				transactionIDEamMapping.put(transactionId , transaction.getEan());
			}
		}
		return totalCustomerBuyingTwiceOrMore / totalTransactions;
	}
}
