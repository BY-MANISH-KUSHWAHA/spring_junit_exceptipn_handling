package com.spring.junit.exception.JunitExceptionHandlingProject.service;

import com.spring.junit.exception.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exception.JunitExceptionHandlingProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImp implements EmployeeService{
    @Autowired
    EmployeeRepository repository;

    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> emp = repository.findById(id);
        if(emp.isPresent()) return emp.get();
        return null;
    }

    @Override
    public Employee addEmployee(Employee emp){
        return repository.save(emp);
    }

    @Override
    public List<Employee> addEmployees(List<Employee> emp){
        return repository.saveAll(emp);
    }


    @Override
    public void removeEmployeeById(int id) {
            repository.deleteById(id);
    }
}
