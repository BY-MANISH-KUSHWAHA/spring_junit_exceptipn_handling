package com.spring.junit.exception.JunitExceptionHandlingProject.exceptionHandling;


public class EmployeeException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public EmployeeException(){}

    public EmployeeException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }





}
