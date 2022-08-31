package com.bootcamphw.hw02.entity;

public class Product {
	private long productId;
    private String productName;
    private double salesPrice;

    public Product(long i, String s, double v) {
        this.productId = i;
        this.productName = s;
        this.salesPrice = v;
    }

    public double getSalesPrice() {
         return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
         this.salesPrice = salesPrice;
    }

    public String getProductName() {
         return productName;
    }

    public void setProductName(String productName) {
         this.productName = productName;
    }

    public long getProductId() {
         return productId;
    }
}
