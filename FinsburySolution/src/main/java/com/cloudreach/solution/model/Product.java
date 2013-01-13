package com.cloudreach.solution.model;

public class Product implements Comparable<Product>{
	
	private String eam;
	
	private String productName;
	
	private Double totalProfit;

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

	@Override
	public int compareTo(Product product) {
		if(this.totalProfit > product.getTotalProfit()){
			return -1;
		}else if(this.totalProfit == product.getTotalProfit()){
			return 0;
		}else{
			return 1;
		} 
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

}
