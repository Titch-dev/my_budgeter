package com.ldp.budgeter.data;

import com.ldp.budgeter.model.CustomerBudget;

import java.util.List;

public interface CustomerBudgetRepo {

    List<CustomerBudget> findAllById(int customerId);

    CustomerBudget findById(int customerId, int budgetId);

    boolean add(CustomerBudget customerBudget);

    boolean update(CustomerBudget customerBudget);

    boolean deleteById(int customerId, int budgetId);

}
