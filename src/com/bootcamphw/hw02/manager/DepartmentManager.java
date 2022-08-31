package com.bootcamphw.hw02.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamphw.hw02.entity.Department;

public class DepartmentManager  extends BaseManager<Department>{
	@Override
    public boolean insert(Department entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("INSERT INTO department (departmentname) VALUES (?)");
        statement.setString(1, entity.getDepartmentName());
        int affected=statement.executeUpdate();
        disconnect();
        return affected>0;
    }

    @Override
    public boolean update(Department entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("UPDATE department SET departmentname = ? WHERE departmentid = ?");
        statement.setString(1, entity.getDepartmentName());
        statement.setLong(2, entity.getDepartmentId());
        int affected = statement.executeUpdate();
        connection.close();
        return affected > 0;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("DELETE FROM department WHERE departmentid = ?");
        statement.setLong(1, id);
        var result = statement.executeUpdate();
        disconnect();
        return result > 0;
    }

    @Override
    public List<Department> list() throws SQLException {
        var connection = connect();
        var sql = "SELECT * FROM department";
        var statement = connection.prepareStatement(sql);
        var resultSet = statement.executeQuery();
        var list = parseList(resultSet);
        disconnect();
        return list;
    }

    @Override
    public Department find(long id) throws SQLException {
        var connection = connect();
        var sql = "SELECT * FROM department WHERE departmentid = ?";
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        var resultSet = statement.executeQuery();
        var entity = parse(resultSet);
        disconnect();
        return entity;
    }

    @Override
    protected Department parse(ResultSet resultSet) throws SQLException {
        long departmentId = resultSet.getLong("departmentid");
        String departmentName = resultSet.getString("departmentname");
        return new Department(departmentId, departmentName);
    }
}
