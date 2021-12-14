package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerJdbcTRepoTest {

    @Autowired
    CustomerJdbcTRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindJoe() {
        Customer joe = repo.findById(1);
        assertNotNull(joe);
        assertEquals(1, joe.getCustomerId());
        assertEquals("joe", joe.getFirstName());
        assertEquals(2220.00, joe.getCurrentIn());
    }

    @Test
    void shouldAddHazel() {
        Customer hazel = makeCustomer();
        Customer actual = repo.add(hazel);
        assertNotNull(actual);
        assertEquals(2, actual.getCustomerId());
    }

    @Test
    void shouldUpdateJoe() {
        Customer customer = makeCustomer();
        customer.setCustomerId(1);
        assertTrue(repo.update(customer));
        assertEquals("Hazel", repo.findById(1).getFirstName());
    }

    @Test
    void shouldDeleteJoe(){
        assertTrue(repo.deleteById(1));
        assertFalse(repo.deleteById(1));
    }

    private Customer makeCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Hazel");
        customer.setEmail("h@hazel.com");
        customer.setCurrentBal(100.00F);
        return customer;
    }
}
