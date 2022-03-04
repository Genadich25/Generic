package com.curstwo.generic.data;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String fullName = firstName + " " + lastName;

    public Employee(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.fullName = firstName + " " + secondName;
    }

    public String getFullName() {
        return fullName;
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