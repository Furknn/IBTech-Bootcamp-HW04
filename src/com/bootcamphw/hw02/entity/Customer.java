package com.bootcamphw.hw02.entity;

public class Customer {
	long customerId;
    String customerName;
    double totalDebit;

    public Customer(){}

    public Customer(long customerId, String customerName, double totalDebit) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalDebit = totalDebit;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }
}
