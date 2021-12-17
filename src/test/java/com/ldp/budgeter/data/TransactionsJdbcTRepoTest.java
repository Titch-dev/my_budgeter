package com.ldp.budgeter.data;


import com.ldp.budgeter.model.Transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TransactionsJdbcTRepoTest {

    @Autowired
    TransactionsJdbcTRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldAllJoeTransactions() {
        List<Transactions> actual = repo.findAll(1);
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    void shouldFindMainJob() {
        Transactions mainJob = repo.findById(1);
        assertNotNull(mainJob);
        assertEquals("main job", mainJob.getSubCategory());
    }

    @Test
    void shouldAddTransaction() {
        Transactions trans = makeTransaction();
        Transactions actual = repo.add(trans);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdateTransaction(){
        Transactions trans = makeTransaction();
        trans.setTransId(2);
        assertTrue(repo.update(trans));
        assertEquals("week", repo.findById(2).getFrequency());

    }

    @Test
    void shouldDeleteTransaction(){
        assertTrue(repo.deleteById(1));
        assertFalse(repo.deleteById(1));
    }

    private Transactions makeTransaction(){
        Transactions t = new Transactions();
        t.setDirection("out");
        t.setBudgetId(7);
        t.setSubCategory("test");
        t.setTransDate(Date.valueOf("2021-12-12"));
        t.setTransAmount(15.00F);
        t.setFrequency("week");
        t.setCustomerId(1);
        return t;

    }
}
