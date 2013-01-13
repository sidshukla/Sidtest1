package com.cloudreach.solution.model;

public class Brand implements Comparable<Brand>{
	
	private String brand;
	
	private int quantity;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Brand brand) {
		if(this.quantity > brand.getQuantity()){
			return -1;
		}else if(this.quantity == brand.getQuantity()){
			return 0;
		}else{
			return 1;
		} 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + quantity;
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
		Brand other = (Brand) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
