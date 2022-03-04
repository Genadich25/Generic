package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
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
    public Object addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.addEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/remove")
    public Object deleteEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/find")
    public Object findEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.findEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/print")
    public Map<String, Employee> print(){
        return employeeService.print();
    }
}