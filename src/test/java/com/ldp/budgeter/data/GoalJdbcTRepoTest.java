package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GoalJdbcTRepoTest {

    @Autowired
    GoalJdbcTRepo repo;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set();}

    @Test
    void shouldFindById(){
        Goal goal = repo.findById(1);
        assertNotNull(goal);
    }

    @Test
    void shouldAddGoal(){
        Goal test = makeGoal();
        Goal actual = repo.add(test);
        assertNotNull(actual);
        assertEquals("test", actual.getGoalName());
    }

    @Test
    void shouldUpdateBalance(){
        Goal test = makeGoal();
        test.setGoalId(1);
        assertTrue(repo.update(test));
        assertEquals("test", repo.findById(1).getGoalName());
    }

    @Test
    // set_known_good_state() creates 1 row
    void shouldDelete(){
        assertTrue(repo.deleteById(1));
        assertFalse(repo.deleteById(1));
    }

    private Goal makeGoal(){
        Goal goal = new Goal();
        goal.setGoalName("test");
        goal.setTargetAmount(1000);
        goal.setTargetDate(Date.valueOf("2022-02-15"));
        goal.setAchieved(false);
        goal.setCustomerId(1);
        return goal;
    }
}
