package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Employee emp1 = new Employee("Григорий","Иванов", 1, 55000);
    Employee emp2 = new Employee("Иван","Иванов",1, 51000);
    Employee emp3 = new Employee("Михаил","Петрович",1, 66000);
    Employee emp4 = new Employee("Павел","Сергеевич",2, 61000);
    Employee emp5 = new Employee("Петр","Григорьевич",2, 64000);
    Employee emp6 = new Employee("Игорь","Дмитреевич",2, 53000);
    Employee emp7 = new Employee("Евгений","Олегович",2, 87000);
    Employee emp8 = new Employee("Александр","Николаевич",3, 44000);
    Employee emp9 = new Employee("Владимир","Витальевич",3, 56000);



    Map<String, Employee> employees = new HashMap<>(Map.of(
            emp1.getFullName() , emp1,
            emp2.getFullName() , emp2,
            emp3.getFullName() , emp3,
            emp4.getFullName() , emp4,
            emp5.getFullName() , emp5,
            emp6.getFullName() , emp6,
            emp7.getFullName() , emp7,
            emp8.getFullName() , emp8,
            emp9.getFullName() , emp9
    ));

    public Map<String, Employee> print(){
        return employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        if(!StringUtils.isAlpha(firstName)){
            throw new BadRequestException();
        }
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + " " + lastName)) {
            throw new EmployeeNotFoundException();
        }

        employees.put(firstName + " " + lastName, employee);
        return employee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.get(firstName + " " + lastName) != null) {
            employees.remove(firstName + " " + lastName);
            return employee;
        }

        throw new NotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + " " + lastName)) {
            return employees.get(firstName + " " + lastName);
        }
        throw new NotFoundException();
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }
}