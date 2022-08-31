package com.bootcamphw.hw02.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamphw.hw02.entity.Employee;

public class EmployeeManager extends BaseManager<Employee>{
	@Override
    public boolean insert(Employee entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("INSERT INTO employee (employeename,monthlysalary,departmentid) VALUES (?,?,?)");
        statement.setString(1, entity.getEmployeeName());
        statement.setDouble(2, entity.getMonthlySalary());
        statement.setLong(3, entity.getDepartment().getDepartmentId());
        int affected = statement.executeUpdate();
        disconnect();
        return affected > 0;

    }

    @Override
    public boolean update(Employee entity) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("UPDATE employee SET employeename = ?, monthlysalary = ?, departmentid = ? WHERE employeeid = ?");
        statement.setString(1, entity.getEmployeeName());
        statement.setDouble(2, entity.getMonthlySalary());
        statement.setLong(3, entity.getDepartment().getDepartmentId());
        statement.setLong(4, entity.getEmployeeId());
        int affected = statement.executeUpdate();
        connection.close();
        return affected > 0;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        var connection = connect();
        var statement = connection.prepareStatement("DELETE FROM employee WHERE employeeid = ?");
        statement.setLong(1, id);
        var result = statement.executeUpdate();
        disconnect();
        return result > 0;
    }

    @Override
    public List<Employee> list() throws SQLException {
        var connection = connect();
        var sql = "SELECT * FROM employee";
        var statement = connection.prepareStatement(sql);
        var resultSet = statement.executeQuery();
        var list = parseList(resultSet);
        disconnect();
        return list;
    }

    @Override
    public Employee find(long id) throws SQLException {
        var connection = connect();
        var sql = "SELECT * FROM employee WHERE employeeid = ?";
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        var resultSet = statement.executeQuery();
        var entity = parse(resultSet);
        disconnect();
        return entity;
    }

    protected Employee parse(ResultSet resultSet) throws SQLException {
        long employeeId=resultSet.getLong("employeeid");
        String employeeName=resultSet.getString("employeename");
        double monthlySalary=resultSet.getDouble("monthlysalary");
        return new Employee(employeeId,employeeName,monthlySalary);
    }
}
