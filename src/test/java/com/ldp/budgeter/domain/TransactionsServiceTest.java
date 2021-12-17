package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.TransactionsRepo;
import com.ldp.budgeter.model.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TransactionsServiceTest {

    @MockBean
    TransactionsRepo repo;

    @Autowired
    TransactionsService service;

    @Test
    void shouldAdd(){
        Transactions transactionIn = new Transactions(0, "in", 1, 1);
        Transactions transactionOut = new Transactions(22,"in",1, 1);

        when(repo.add(transactionIn)).thenReturn(transactionOut);
        Result<Transactions> actual = service.add(transactionIn);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotAddNull(){
        Result<Transactions> actual = service.add(null);
        assertEquals(ResultType.INVALID, actual.getType());
        assertNull(actual.getPayload());
    }

    @Test
    void shouldNotAddEmptyBudgetId(){
        Transactions transactionIn = new Transactions(0, "in", 0, 1);
        Result<Transactions> actual = service.add(transactionIn);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate(){
        Transactions transIn = new Transactions(3, "out", 1, 1);
        when(repo.update(transIn)).thenReturn(true);

        Result<Transactions> actual = service.update(transIn);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing(){
        Transactions transIn = new Transactions(100, "in", 1,1);
        Result<Transactions> actual = service.update(transIn);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalid(){
        Transactions transIn = new Transactions(1, "", 1,1);

        Result<Transactions> actual = service.update(transIn);
        assertEquals(ResultType.INVALID, actual.getType());

        transIn.setDirection("out");
        transIn.setBudgetId(13);
        actual = service.update(transIn);
        assertEquals(ResultType.INVALID, actual.getType());

        transIn.setBudgetId(1);
        transIn.setCustomerId(0);
        actual = service.update(transIn);
        assertEquals(ResultType.INVALID, actual.getType());

        transIn.setTransId(0);
        transIn.setCustomerId(1);
        actual = service.update(transIn);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
