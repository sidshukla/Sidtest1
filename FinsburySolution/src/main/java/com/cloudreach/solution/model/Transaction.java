package com.cloudreach.solution.model;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {

	private Date transactionDate;

	private long transactionId;

	private String ean;

	private int quantity;

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result
				+ (int) (transactionId ^ (transactionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (ean == null) {
			if (other.ean != null)
				return false;
		} else if (!ean.equals(other.ean))
			return false;
		if (quantity != other.quantity)
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Transaction arg0) {
		if(this.quantity > arg0.getQuantity()){
			return 0;
		}else if(this.quantity == arg0.quantity){
			return 0;
		}else{
			return 1;
		}
		
	}
}
