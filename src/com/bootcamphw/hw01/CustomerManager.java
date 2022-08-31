package com.bootcamphw.hw01;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
	private String url =   "jdbc:postgresql://localhost/bootcampdb";
    private String user = "sa";
    private String password = "Hotel6**";

    public boolean insert (Customer customer) throws SQLException {
        var conn= DriverManager.getConnection(url,user,password);
        var sql="insert into customer(customername,totaldebit) values(?,?)";
        var statement=conn.prepareStatement(sql);
        statement.setString(1,customer.getCustomerName());
        statement.setDouble(2,customer.getTotalDebit());
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public boolean update(Customer customer) throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="update customer set customername=?,totaldebit=? where customerid=?";
        var statement=conn.prepareStatement(sql);
        statement.setString(1,customer.getCustomerName());
        statement.setDouble(2,customer.getTotalDebit());
        statement.setLong(3,customer.getCustomerId());
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public boolean delete(long id) throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="delete from customer where customerid=?";
        var statement=conn.prepareStatement(sql);
        statement.setLong(1,id);
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public List<Customer> list() throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="select * from customer";
        var statement=conn.prepareStatement(sql);
        var resultSet=statement.executeQuery();
        var customer=parseList(resultSet);
        conn.close();
        return customer;
    }

    public Customer find(long id) throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="select * from customer where customerid=?";
        var statement=conn.prepareStatement(sql);
        statement.setLong(1,id);
        var resultSet=statement.executeQuery();
        Customer customer=parse(resultSet);
        conn.close();
        return customer;
    }


    private Customer parse(ResultSet resultSet) throws SQLException {
        long customerId = resultSet.getLong("customerid");
        String customerName = resultSet.getString("customername");
        double totalDebit = resultSet.getDouble("totaldebit");
        return new Customer(customerId,customerName,totalDebit);
    }

    private List<Customer> parseList(ResultSet resultSet) throws SQLException{
        List<Customer> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(parse(resultSet));
        }
        return list;
    }
}
