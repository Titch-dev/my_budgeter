package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Customer;
import com.ldp.budgeter.model.CustomerBudget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerBudgetJdbcTRepoTest {

    @Autowired
    CustomerBudgetJdbcTRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindAll(){
        List<CustomerBudget> all = repo.findAllById(1);
        assertNotNull(all);
        assertEquals(2, all.size());
        assertEquals(0.00, all.get(1).getUserDefBal());
    }

    @Test
    void shouldFindById(){
        CustomerBudget actual = repo.findById(1,8);
        assertNotNull(actual);
    }

    @Test
    void shouldAdd(){
        CustomerBudget test = makeCustomerBudget();
        assertTrue(repo.add(test));
    }

    @Test
    void shouldUpdate(){
        CustomerBudget test = makeCustomerBudget();
        assertTrue(repo.update(test));
    }

    @Test
    void shouldDelete(){
        assertTrue(repo.deleteById(1, 8));
        assertFalse(repo.deleteById(1,8));
    }

    public CustomerBudget makeCustomerBudget(){
        CustomerBudget cb = new CustomerBudget();
        cb.setCustomerId(1);
        cb.setBudgetId(4);
        cb.setAccurateBal(275);
        cb.setUserDefBal(300);
        return cb;
    }
}
