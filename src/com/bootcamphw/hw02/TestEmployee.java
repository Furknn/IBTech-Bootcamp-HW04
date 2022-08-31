package com.bootcamphw.hw02;

import java.sql.SQLException;

import com.bootcamphw.hw02.entity.Employee;
import com.bootcamphw.hw02.manager.EmployeeManager;

public class TestEmployee {
	public static void main(String[] args) throws SQLException {
		// Insert list of employees
		Employee e1 = new Employee();
		e1.setEmployeeId(1);
		e1.setEmployeeName("Ali");
		e1.setMonthlySalary(100.0);
		
		Employee e2 = new Employee();
		e2.setEmployeeId(2);
		e2.setEmployeeName("Ahmet");
		e2.setMonthlySalary(200.0);

		var employeeManager=new EmployeeManager();
		employeeManager.insert(e1);
		employeeManager.insert(e2);
		
		// Find employye by id
		var employee=employeeManager.find(1);
		System.out.println(employee.getEmployeeName());

		// List employee
		var list=employeeManager.list();
		for (var item : list) {
			System.out.println(item.getEmployeeName());
		}

	}
}
