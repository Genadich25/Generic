package com.curstwo.generic.data;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer departmentId;
    private Integer salary;

    public Employee(String firstName, String secondName, Integer departmentId, Integer salary) {
        this.salary = salary;
        this.departmentId = departmentId;
        this.firstName = firstName;
        this.lastName = secondName;
        this.fullName = firstName + " " + secondName;
    }

    public Employee(String firstName, String secondName) {
        this.salary = 50000;
        this.departmentId = 1;
        this.firstName = firstName;
        this.lastName = secondName;
        this.fullName = firstName + " " + secondName;
    }

    public boolean isDepartmentId(int departmentId){
        return departmentId == this.departmentId;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return this.firstName + "  " + this.lastName;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if(other == null || this.getClass() != other.getClass()){
            return false;
        }
        Employee emp = (Employee) other;
        if(this.firstName.equals(emp.getFirstName())){
            return this.lastName.equals(emp.getLastName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}