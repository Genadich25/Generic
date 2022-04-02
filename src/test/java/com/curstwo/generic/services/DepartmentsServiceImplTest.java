package com.curstwo.generic.services;
import com.curstwo.generic.data.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceImplTest {

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee empEmpty;
    private Map<String, Employee> map;
    private DepartmentsServiceImpl service;

    @Mock
    public EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp(){
        emp1 = new Employee("Петр", "Гришин", 1, 53000);
        emp2 = new Employee("Иван","Иванов",1, 60000);
        emp3 = new Employee("Анна", "Гришина", 2, 55000);
        emp4 = new Employee("Сергей","Пушкин",1, 45000);
        map = new HashMap<>(Map.of(
                emp1.getFullName(),emp1,
                emp2.getFullName(),emp2,
                emp3.getFullName(),emp3,
                emp4.getFullName(),emp4));
        empEmpty = null;
        service = new DepartmentsServiceImpl(employeeService);
    }

    @Test
    public void printAll(){
        assertNotNull(employeeService);

        Mockito.when(employeeService.getEmployees()).thenReturn(map);
        List<Employee> employees= new ArrayList<>(employeeService.getEmployees().values());
        Assertions.assertEquals(service.printAll(), employees);
    }

    @Test
    public void printAllDepartmentId(){
        assertNotNull(employeeService);
        int numDepartment = 1;
        Mockito.when(employeeService.getEmployees()).thenReturn(map);

        Map<String, Employee> employeeMap = employeeService.getEmployees().values().stream()
                        .filter(employee -> employee.isDepartmentId(numDepartment))
                .collect(Collectors.toMap(Employee::toString, e -> e));
        Assertions.assertEquals(service.printAll(numDepartment), employeeMap);
    }

    @Test
    public void maxSalary(){
        assertNotNull(employeeService);
        int numDepartment = 1;
        Mockito.when(employeeService.getEmployees()).thenReturn(map);

        Assertions.assertEquals(service.maxSalary(numDepartment), emp2);
    }

    @Test
    public void minSalary(){
        assertNotNull(employeeService);
        int numDepartment = 1;
        Mockito.when(employeeService.getEmployees()).thenReturn(map);

        Assertions.assertEquals(service.minSalary(numDepartment), emp4);
    }

}
