package com.bootcamphw.hw02.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamphw.hw02.entity.Customer;

public class CustomerManager extends BaseManager<Customer> {
	@Override
    public boolean insert(Customer entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("INSERT INTO customer (customername, totaldebit) VALUES (?, ?)");
        statement.setString(1, entity.getCustomerName());
        statement.setDouble(2, entity.getTotalDebit());
        int affected = statement.executeUpdate();
        disconnect();
        return affected > 0;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("UPDATE customer SET customername = ? WHERE customerid = ?");
        statement.setString(1, entity.getCustomerName());
        statement.setLong(2, entity.getCustomerId());
        int affected = statement.executeUpdate();
        connection.close();
        return affected > 0;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("DELETE FROM customer WHERE customerid = ?");
        statement.setLong(1, id);
        var result = statement.executeUpdate();
        disconnect();
        return result > 0;
    }

    @Override
    public List<Customer> list() throws SQLException {
        var connection= connect();
        var sql="SELECT * FROM customer";
        var statement=connection.prepareStatement(sql);
        var resultSet=statement.executeQuery();
        var list=parseList(resultSet);
        disconnect();
        return list;

    }

    @Override
    public Customer find(long id) throws SQLException {
        var connection = connect();
        var sql = "SELECT * FROM customer WHERE customerid = ?";
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        var resultSet = statement.executeQuery();
        var entity = parse(resultSet);
        disconnect();
        return entity;
    }

    @Override
    protected Customer parse(ResultSet resultSet) throws SQLException {
        var customerId = resultSet.getLong("customerid");
        var customerName = resultSet.getString("customername");
        var totalDebit = resultSet.getDouble("totaldebit");
        return new Customer(customerId, customerName, totalDebit);
    }
}
