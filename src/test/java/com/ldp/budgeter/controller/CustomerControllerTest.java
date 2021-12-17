package com.ldp.budgeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldp.budgeter.data.CustomerRepo;
import com.ldp.budgeter.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @MockBean
    CustomerRepo repo;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldFindById() throws Exception{
        Customer mockCustomer = new Customer(1,"test","test");

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(mockCustomer);

        when(repo.findById(any())).thenReturn(mockCustomer);

        mvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldAdd() throws Exception{
        Customer customerIn = new Customer(0, "test", "test");
        Customer expected = new Customer(1, "test", "test");

        when(repo.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(customerIn);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}
