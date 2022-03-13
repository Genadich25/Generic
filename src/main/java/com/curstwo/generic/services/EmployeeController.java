package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.EmployeeNotFoundException;
import com.curstwo.generic.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    public final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String hello(){
        return "Сервер работает!";
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @ResponseStatus
    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.findEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/print")
    public Map<String, Employee> print(){
        return employeeService.print();
    }
}