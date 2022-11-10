package com.spring.junit.exception.JunitExceptionHandlingProject.controller;

import com.jayway.jsonpath.JsonPath;
import com.spring.junit.exception.JunitExceptionHandlingProject.JunitExceptionHandlingProjectApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class) // for running this with junit4
@ContextConfiguration(classes = JunitExceptionHandlingProjectApplication.class) // Run it setup method for default data
@SpringBootTest // spring test
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // to excute the test methods in order (based on name)
public class EmployeeControllerTest {

    // For controller based mocks
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext employeeContext; // autowired the configuration

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(employeeContext).build();
    }

    @Test
    public void printCheckJsonBody() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/employee/5").
                        accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Test
    public void verufyAllEmployee() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/employee").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$",hasSize(3))).andDo(print());
    }

    @Test
    public void verifyGetById() throws Exception{
//        Content type = application/json
//        Body = {"id":2,"name":"hriti","email":"hk9313","password":"12345678"}
//        Forwarded URL = null
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/employee/2").
                        accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").value("hk9313"))
                .andExpect(jsonPath("$.password").exists())
                .andDo(print());
    }

    @Test
    public void verifyInValidEmployee_BAD_REQUEST() throws Exception{
//        Content type = application/json
//        Body = {"errorCode":400,"message":"THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX."}
//        Forwarded URL = null
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                get("/employee/add").// Wrong method instead of Post we are using Get
                                accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX."))
                .andDo(print());
    }

    @Test
    public void verifyInValidEmployee_NOT_FOUND_INVALID_ID() throws Exception{
//        Content type = application/json
//        Body = {"errorCode":404,"message":"EMPLOYEE DOESN't EXIST"}
//        Forwarded URL = null
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                get("/employee/5").
                                accept(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("EMPLOYEE DOESN't EXIST"))
                .andDo(print());
    }





}
