package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Employee emp1 = new Employee("Григорий","Иванов");
    Employee emp2 = new Employee("Иван","Иванов");
    Employee emp3 = new Employee("Михаил","Петрович");

    Map<String, Employee> employees = new HashMap<>(Map.of(
            emp1.getFullName() , emp1,
            emp2.getFullName() , emp2,
            emp3.getFullName() , emp3
    ));

    public Map<String, Employee> print(){
        return employees;
    }

    public Object addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + " " + lastName)) {
            throw new EmployeeNotFoundException();
        }

        employees.put(firstName + " " + lastName, employee);
        return employee;
    }

    public Object deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.get(firstName + " " + lastName) != null) {
            employees.remove(firstName + " " + lastName);
            return employee;
        }

        throw new NotFoundException();
    }

    public Object findEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + " " + lastName)) {
            return employees.get(firstName + " " + lastName);
        }
        throw new NotFoundException();
    }
}