package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentsServiceImpl implements DepartmentService{
    public EmployeeServiceImpl employeeService;
    public DepartmentsServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> printAll(){
        return employeeService.getEmployees().values().stream()
                .sorted(Comparator.comparing(Employee::getDepartmentId))
                .collect(Collectors.toList());
    }

    public Map<String, Employee> printAll(int departmentId) {
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .collect(Collectors.toMap(Employee::toString, e -> e));
    }

    public Employee maxSalary(int departmentId){
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }

    public Employee minSalary(int departmentId){
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .get();
    }
}
