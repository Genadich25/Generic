package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Map<String, Employee> print();

    public Object addEmployee(String firstName, String lastName);

    public Object deleteEmployee(String firstName, String lastName);

    public Object findEmployee(String firstName, String lastName);

}