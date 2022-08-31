package com.bootcamphw.hw02.manager;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamphw.hw02.entity.Product;

public class ProductManager extends BaseManager<Product> {
	public boolean insert (Product product) throws SQLException {
        var conn= DriverManager.getConnection(url,user,password);
        var sql="insert into product(productname,salesprice) values(?,?)";
        var statement=conn.prepareStatement(sql);
        statement.setString(1,product.getProductName());
        statement.setDouble(2,product.getSalesPrice());
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public boolean update(Product product) throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="update product set productname=?,salesprice=? where productid=?";
        var statement=conn.prepareStatement(sql);
        statement.setString(1,product.getProductName());
        statement.setDouble(2,product.getSalesPrice());
        statement.setLong(3,product.getProductId());
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public boolean delete(long id) throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="delete from product where productid=?";
        var statement=conn.prepareStatement(sql);
        statement.setLong(1,id);
        int affected=statement.executeUpdate();
        conn.close();
        return affected>0;
    }

    public List<Product> list() throws SQLException {
        var conn=DriverManager.getConnection(url,user,password);
        var sql="select * from product";
        var statement=conn.prepareStatement(sql);
        var resultSet=statement.executeQuery();
        var products=parseList(resultSet);
        conn.close();
        return products;
    }

    public Product find(long id) throws SQLException {
        var conn = DriverManager.getConnection(url, user, password);
        var sql = "select * from product where productid=?";
        var statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        var resultSet = statement.executeQuery();
        Product product = parse(resultSet);
        conn.close();
        return product;
    }

    protected Product parse(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("productid");
        String productName = resultSet.getString("productname");
        double salesPrice = resultSet.getDouble("salesprice");
        return new Product(productId,productName,salesPrice);
    }

    public List<Product> listBySalesPriceGreater(double salesPriceMin) {
        try {
            var conn = DriverManager.getConnection(url, user, password);
            var sql = "select * from product where salesprice> ? order by salesprice desc";
            var statement = conn.prepareStatement(sql);
            statement.setDouble(1, salesPriceMin);
            var resultSet = statement.executeQuery();
            conn.close();
            return parseList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
