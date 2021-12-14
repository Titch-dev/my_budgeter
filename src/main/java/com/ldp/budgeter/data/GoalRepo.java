package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Goal;

public interface GoalRepo {

    Goal findById(int goalId);

    Goal add(Goal goal);

    boolean update(Goal goal);

    boolean deleteById(int goalId);

}
