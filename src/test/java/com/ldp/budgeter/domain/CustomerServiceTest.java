package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.CustomerRepo;
import com.ldp.budgeter.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerServiceTest {

    @MockBean
    CustomerRepo repo;

    @Autowired
    CustomerService service;

    @Test
    void shouldAdd(){
        Customer customerIn = new Customer(0, "test", "test@t.com");
        Customer customerOut = new Customer(1,"test", "test@t.com");

        when(repo.add(customerIn)).thenReturn(customerOut);

        Result<Customer> result = service.add(customerIn);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(customerOut, result.getPayload());
    }

    @Test
    void shouldNotAddNull(){
        Result<Customer> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void ShouldNotAddEmptyEmail(){
        Customer customerIn = new Customer(0,"test", "");
        Result<Customer> result = service.add(customerIn);
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotDelete(){
        when(repo.deleteById(10)).thenReturn(false);
        assertFalse(service.deleteById(10));
    }

    @Test
    void shouldDelete(){
        when(repo.deleteById(10)).thenReturn(true);
        assertTrue(service.deleteById(10));
    }

    @Test
    void shouldUpdate(){
        Customer customerUpdate = new Customer(1,"test","test");

        when(repo.update(customerUpdate)).thenReturn(true);
        Result<Customer> actual = service.update(customerUpdate);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing(){
        Customer customerUpdate = new Customer(100, "test", "test");

        when(repo.update(customerUpdate)).thenReturn(false);
        Result<Customer> actual = service.update(customerUpdate);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalid(){
        Customer customer = new Customer(1, null, "test");

        Result<Customer> actual = service.update(customer);
        assertEquals(ResultType.INVALID, actual.getType());

        customer.setFirstName("test");
        customer.setEmail(" ");
        actual = service.update(customer);
        assertEquals(ResultType.INVALID, actual.getType());

        customer.setEmail("test");
        customer.setCustomerId(0);
        actual = service.update(customer);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
