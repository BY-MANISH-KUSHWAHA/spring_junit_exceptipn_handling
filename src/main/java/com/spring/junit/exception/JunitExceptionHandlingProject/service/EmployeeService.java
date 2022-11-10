package com.spring.junit.exception.JunitExceptionHandlingProject.service;

import com.spring.junit.exception.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exception.JunitExceptionHandlingProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(int id);
    public Employee addEmployee(Employee emp);

    public List<Employee> addEmployees(List<Employee> emp);
    public void removeEmployeeById(int id);


}
