package com.spring.junit.exception.JunitExceptionHandlingProject.service;
import com.spring.junit.exception.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exception.JunitExceptionHandlingProject.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {
    @Mock // Dummy Data
    private EmployeeRepository repository;

    @InjectMocks // Dummy Service for Injecting data
    private EmployeeServiceImp service;

    @Before // Before Each Test Case, ready the mocked data
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void getAllEmployee(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(0,"Manish","mkk9313","1234567"));
        employeeList.add(new Employee(0,"Hriti","hk9313","1234567"));
        employeeList.add(new Employee(0,"Driri","dk9313","1234567"));

        when(repository.findAll()).thenReturn(employeeList);

        List<Employee> employeesResult = service.getAllEmployee();

        assertEquals(3,employeesResult.size());
    }

    @Test
    public void getEmployeeById(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(0,"Manish","mkk9313","1234567"));
        employeeList.add(new Employee(1,"Hriti","hk9313","1234567"));
        employeeList.add(new Employee(2,"Driri","dk9313","1234567"));
        // Auto generate Id with inital value 0
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.ofNullable(employeeList.get(id)));

        Employee employeeResult = service.getEmployeeById(id);

        assertEquals(employeeResult.getId(),1);
        assertEquals("Hriti",employeeResult.getName());
        assertEquals("1234567",employeeResult.getPassword());
        assertEquals("hk9313",employeeResult.getEmail());
    }
    @Test
    public void saveEmployee(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(0,"Manish","mkk9313","1234567"));
        employeeList.add(new Employee(1,"Hriti","hk9313","1234567"));

        // Auto generate Id with inital value 0
        Employee emp = new Employee(2, "Driti", "dk9313", "1234567");
        when(repository.save(emp)).thenReturn(emp);

        Employee employeeResult = service.addEmployee(emp);

        assertEquals(employeeResult.getId(),2);
        assertEquals("Driti",employeeResult.getName());
        assertEquals("1234567",employeeResult.getPassword());
        assertEquals("dk9313",employeeResult.getEmail());
    }
    @Test
    public void deleteEmployeeById(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(0,"Manish","mkk9313","1234567"));
        employeeList.add(new Employee(1,"Hriti","hk9313","1234567"));

        Employee emp = new Employee(2, "Driti", "dk9313", "1234567");
        when(repository.findAll()).thenReturn(employeeList);


        assertEquals(2,service.getAllEmployee().size());

        employeeList.add(emp);
        when(repository.save(emp)).thenReturn(emp);
        // added: Employee emp = new Employee(2, "Driti", "dk9313", "1234567");
        assertEquals(3,service.getAllEmployee().size());

        service.removeEmployeeById(1);
        employeeList.remove(emp);
        assertEquals(2,service.getAllEmployee().size());

    }
    @Test
    public void deleteEmployeeById_With_Verifier(){
        Employee emp_ = new Employee(2, "Driti", "dk9313", "1234567");

        service.removeEmployeeById(emp_.getId());

        verify(repository, times(1)).deleteById(emp_.getId());

    }


}
