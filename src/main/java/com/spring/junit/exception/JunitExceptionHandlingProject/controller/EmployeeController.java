package com.spring.junit.exception.JunitExceptionHandlingProject.controller;

import com.spring.junit.exception.JunitExceptionHandlingProject.exceptionHandling.EmployeeException;
import com.spring.junit.exception.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exception.JunitExceptionHandlingProject.model.PayloadValidation;
import com.spring.junit.exception.JunitExceptionHandlingProject.model.Response;
import com.spring.junit.exception.JunitExceptionHandlingProject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeException {
        Employee employee = service.getEmployeeById(id);
        if(employee==null || employee.getId()<=0){
            throw new EmployeeException("EMPLOYEE DOESN't EXIST");
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteEmployeeById(@PathVariable("id") int id) throws EmployeeException {
        Employee employee = service.getEmployeeById(id);
        if(employee==null || employee.getId()<=0){
            throw new EmployeeException("EMPLOYEE DOESN'T EXIST");
        }
        service.removeEmployeeById(id);
        Response res = new Response(HttpStatus.OK.value(),"Successfully Deleted!");
        return new ResponseEntity<Response>(res,HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee payload) throws EmployeeException{
        if(!PayloadValidation.createPayloadValidation(payload)) {
            throw new EmployeeException("PAYLOAD MALFORMED, ID MUST NOT BE DEFINE");

        }
        System.out.println(payload);
        Employee emp = service.addEmployee(payload);
        return new ResponseEntity<Employee>(emp,HttpStatus.OK);
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public List<Employee> addEmployees(@RequestBody List<Employee> emps){
        return service.addEmployees(emps);
    }

}
