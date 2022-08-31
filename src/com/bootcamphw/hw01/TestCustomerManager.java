package com.bootcamphw.hw01;

import java.sql.SQLException;

public class TestCustomerManager {

	  public static void main(String[] args) {
	        var cm = new CustomerManager();

	        // insert 3 dummy customers to the database with for loop
	        for (int i = 0; i < 3; i++) {
	            var customer = new Customer(i, "customer" + i, i * 100);
	            try {
	                if(cm.insert(customer)) {
	                    System.out.println("inserted");
	                } else {
	                    System.out.println("not inserted");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // update row with customerId = 1
	        var customer = new Customer(1, "customer1", 100);
	        try {
	            if (cm.update(customer)) {
	                System.out.println("updated");
	            } else {
	                System.out.println("not updated");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // delete row with customerId = 2
	        try {
	            if (cm.delete(2)) {
	                System.out.println("deleted");
	            } else {
	                System.out.println("not deleted");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // list all customers
	        try {
	            var customers = cm.list();
	            for (var element : customers) {
	                System.out.println(element.getCustomerId() + " " + element.getCustomerName() + " " + element.getTotalDebit());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    }
}
