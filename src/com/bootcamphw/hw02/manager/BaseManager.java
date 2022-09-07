package com.bootcamphw.hw02.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<T> {
	protected String url="jdbc:postgresql://localhost/bootcampdb";
    protected String user="sa";
    protected String password="123321xp";

    protected Connection connection;

    public BaseManager() {
        try {
            connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection connect(){
        try {
            connection=DriverManager.getConnection(url,user,password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract boolean  insert(T entity) throws SQLException;
    public abstract boolean  update(T entity) throws SQLException;
    public abstract boolean  delete(long id) throws SQLException;
    public abstract List<T> list() throws SQLException;
    public abstract T find(long id) throws SQLException;

    protected void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected List<T> parseList(ResultSet resultSet) throws SQLException{
        List<T> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(parse(resultSet));
        }
        return list;
    }
    protected abstract T parse(ResultSet resultSet) throws SQLException;

}
