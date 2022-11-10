package com.spring.junit.exception.JunitExceptionHandlingProject.util;

import com.spring.junit.exception.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exception.JunitExceptionHandlingProject.model.PayloadValidation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PayloadValidatorTest {
    @Test
    public void validatePayload(){
        Employee employee = new Employee(1,"Manish","mkk9313","1234567");
        // false if id!=0 else true
        assertEquals(false, PayloadValidation.createPayloadValidation(employee));
    }

    @Test
    public void validateInvalidPayload(){
        Employee employee = new Employee(0,"Manish","mkk9313","1234567");
        // false if id!=0 else true
        assertEquals(true, PayloadValidation.createPayloadValidation(employee));
    }
}
