package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Budget;

public interface BudgetRepo {

    Budget findById(int budgetId);

    Budget add(Budget budget);

    Boolean deleteById(int budgetId);

}
