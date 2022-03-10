package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentsServiceImpl implements DepartmentService{
    public EmployeeServiceImpl employeeService;
    public DepartmentsServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Object printAll(){
        return employeeService.getEmployees().values().stream()
                .sorted(Comparator.comparing(Employee::getDepartmentId))
                .collect(Collectors.toList());
    }

    public Object printAll(int departmentId) {
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        Optional<Employee> optionalEmployee = stream.max(Comparator.comparing(Employee::getDepartmentId));
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .collect(Collectors.toMap(Employee::toString, e -> e));
    }

    public Object maxSalary(int departmentId){
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .max(Comparator.comparing(Employee::getSalary));
    }

    public Object minSalary(int departmentId){
        Stream<Employee> stream = employeeService.getEmployees().values().stream();
        return stream
                .filter(employee -> employee.isDepartmentId(departmentId))
                .min(Comparator.comparing(Employee::getSalary));
    }
}
