package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.GoalRepo;
import com.ldp.budgeter.model.Goal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GoalServiceTest {

    @MockBean
    GoalRepo repo;

    @Autowired
    GoalService service;

    @Test
    void shouldAdd(){
        Goal goalIn = new Goal(0, "test", 10.00F, 1);
        Goal goalOut = new Goal(1, "test", 10.00F, 1);

        when(repo.add(goalIn)).thenReturn(goalOut);
        Result<Goal> actual = service.add(goalIn);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotAddNull(){
        Result<Goal> actual = service.add(null);
        assertEquals(ResultType.INVALID, actual.getType());
        assertNull(actual.getPayload());
    }

    @Test
    void shouldUpdate(){
        Goal goalIn = new Goal(1,"test", 10.00F,1);

        when(repo.update(goalIn)).thenReturn(true);

        Result<Goal> actual = service.update(goalIn);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalid(){
        Goal goalIn = new Goal(1,"",10.00F,1);

        Result<Goal> actual = service.update(goalIn);
        assertEquals(ResultType.INVALID, actual.getType());

        goalIn.setGoalId(0);
        goalIn.setGoalName("test");
        actual = service.update(goalIn);
        assertEquals(ResultType.INVALID, actual.getType());

        goalIn.setGoalId(1);
        goalIn.setTargetAmount(0.00F);
        actual = service.update(goalIn);
        assertEquals(ResultType.INVALID, actual.getType());

        goalIn.setCustomerId(0);
        goalIn.setTargetAmount(10.00F);
        actual = service.update(goalIn);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
