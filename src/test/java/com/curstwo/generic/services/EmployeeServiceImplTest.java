package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.BadRequestException;
import com.curstwo.generic.exceptions.EmployeeNotFoundException;
import java.util.stream.Stream;

import com.curstwo.generic.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EmployeeServiceImplTest {

    private Employee emp1;
    private final static Employee emp2 = new Employee("12234", "Иванов", 1, 50000);
    private final static Employee emp3 = new Employee("Иван", "=+==.", 1, 50000);
    private final static Employee emp4 = new Employee("12234", "=_-=-=+", 1, 50000);
    private Employee emp5;
    private EmployeeServiceImpl service;

    @BeforeEach
    private void SetUp(){
        emp1 = new Employee("Петр", "Гришин", 2, 55000);
        emp5 = new Employee("Иван","Иванов",1, 51000);
        service = new EmployeeServiceImpl();
    }

    @Test
    public void addEmployee(){
        service.addEmployee(emp1.getFirstName(), emp1.getLastName());

        Assertions.assertTrue(service.getEmployees().containsKey(emp1.getFullName()));
        Assertions.assertTrue(service.getEmployees().containsValue(emp1));
    }

    public static Stream<Arguments> argumentsStream(){
        return Stream.of(
                Arguments.of(emp2.getFirstName(), emp2.getLastName()),
                Arguments.of(emp3.getFirstName(), emp3.getLastName()),
                Arguments.of(emp4.getFirstName(), emp4.getLastName()));
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void addEmployeeBadRequestException(String firstName, String lastName){
        Assertions.assertThrows(BadRequestException.class, () -> service.addEmployee(firstName, lastName));
    }

    @Test
    public void addEmployeeEmployeeNotFoundException(){
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.addEmployee(emp5.getFirstName(), emp5.getLastName()));
    }

    @Test
    public void deleteEmployee(){
        Assertions.assertTrue(service.getEmployees().containsKey(emp5.getFullName()));

        service.deleteEmployee(emp5.getFirstName(), emp5.getLastName());

        Assertions.assertFalse(service.getEmployees().containsKey(emp5.getFullName()));
        Assertions.assertFalse(service.getEmployees().containsValue(emp5));
    }

    @Test
    public void deleteEmployeeNotFoundException(){
        Assertions.assertThrows(NotFoundException.class, () -> service.deleteEmployee(emp1.getFirstName(), emp1.getLastName()));
    }

    @Test
    public  void findEmployee(){
        Assertions.assertEquals(emp5,service.findEmployee(emp5.getFirstName(), emp5.getLastName()));
    }

    @Test
    public void findEmployeeNotFoundException(){
        service.deleteEmployee(emp5.getFirstName(), emp5.getLastName());

        Assertions.assertThrows(NotFoundException.class, () -> service.findEmployee(emp5.getFirstName(), emp5.getLastName()));
    }


}
