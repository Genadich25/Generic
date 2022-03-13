package com.curstwo.generic.services;

import com.curstwo.generic.data.Employee;
import com.curstwo.generic.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    public final DepartmentsServiceImpl departmentsService;

    public DepartmentController(DepartmentsServiceImpl departmentsService){
        this.departmentsService = departmentsService;
    }

    @GetMapping
    public String hello(){
        return "Сервер Department работает!";
    }

    @GetMapping( path ="/all")
    public List<Employee> printAllDepartment(){
        return departmentsService.printAll();
    }

    @GetMapping( path ="/all", params = {"departmentId"})
    public <departmentId> Map<String, Employee> printAllDepartment(int departmentId){
        return departmentsService.printAll(departmentId);
    }

    @GetMapping( path ="/max-salary")
    public Employee maxSalary(@RequestParam int departmentId){
        return departmentsService.maxSalary(departmentId);
    }

    @GetMapping( path ="/min-salary")
    public Employee minSalary(@RequestParam int departmentId){
        return departmentsService.minSalary(departmentId);
    }
}
