package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BudgetJdbcTRepoTest {

    @Autowired
    BudgetJdbcTRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){ knownGoodState.set(); }

//    id
//    1 = savings
//    2 = leisure
//    3 = shelter
//    4 = transport
//    5 = food
//    6 = credit
//    7 = goal
//    8 = salary
//    9 = investments
//    10 = other

    @Test
    void shouldFindShelter(){
        Budget shelter = repo.findById(3);
        assertNotNull(shelter);
        assertEquals("shelter", shelter.getBudgetName());
    }

    @Test
    void addNewTestBudget(){
        Budget test = makeBudget();
        Budget actual = repo.add(test);
        assertNotNull(actual);
        assertEquals("other", actual.getBudgetName());

    }

    @Test
    void shouldDeleteCredit(){
        assertTrue(repo.deleteById(6));
        assertFalse(repo.deleteById(6));
    }


    private Budget makeBudget(){
        Budget budget = new Budget();
        budget.setBudgetName("other");
        return budget;
    }

}
