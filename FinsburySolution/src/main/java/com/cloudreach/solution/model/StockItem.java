package com.cloudreach.solution.model;

public class StockItem {

	private long ean;

	private String brandName;

	private String productName;

	private String quantity;

	private double wholesalePrice;

	private String wholesalePriceCurrency;

	private double sellingPrice;

	private String sellingPriceCurrency;

	private int batchSize;

	public long getEan() {
		return ean;
	}

	public void setEan(long ean) {
		this.ean = ean;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getWholesalePriceCurrency() {
		return wholesalePriceCurrency;
	}

	public void setWholesalePriceCurrency(String wholesalePriceCurrency) {
		this.wholesalePriceCurrency = wholesalePriceCurrency;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getSellingPriceCurrency() {
		return sellingPriceCurrency;
	}

	public void setSellingPriceCurrency(String sellingPriceCurrency) {
		this.sellingPriceCurrency = sellingPriceCurrency;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchSize;
		result = prime * result
				+ ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + (int) (ean ^ (ean >>> 32));
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sellingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((sellingPriceCurrency == null) ? 0 : sellingPriceCurrency
						.hashCode());
		temp = Double.doubleToLongBits(wholesalePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((wholesalePriceCurrency == null) ? 0
						: wholesalePriceCurrency.hashCode());
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
		StockItem other = (StockItem) obj;
		if (batchSize != other.batchSize)
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (ean != other.ean)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (Double.doubleToLongBits(sellingPrice) != Double
				.doubleToLongBits(other.sellingPrice))
			return false;
		if (sellingPriceCurrency == null) {
			if (other.sellingPriceCurrency != null)
				return false;
		} else if (!sellingPriceCurrency.equals(other.sellingPriceCurrency))
			return false;
		if (Double.doubleToLongBits(wholesalePrice) != Double
				.doubleToLongBits(other.wholesalePrice))
			return false;
		if (wholesalePriceCurrency == null) {
			if (other.wholesalePriceCurrency != null)
				return false;
		} else if (!wholesalePriceCurrency.equals(other.wholesalePriceCurrency))
			return false;
		return true;
	}

}
