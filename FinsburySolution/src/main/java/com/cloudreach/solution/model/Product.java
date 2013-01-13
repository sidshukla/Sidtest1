package com.cloudreach.solution.model;

public class Product implements Comparable<Product>{
	
	private String eam;
	
	private String productName;
	
	private Double profit;

	public String getEam() {
		return eam;
	}

	public void setEam(String eam) {
		this.eam = eam;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	@Override
	public int compareTo(Product product) {
		if(this.profit > product.getProfit()){
			return -1;
		}else if(this.profit == product.getProfit()){
			return 0;
		}else{
			return 1;
		} 
	}

}
