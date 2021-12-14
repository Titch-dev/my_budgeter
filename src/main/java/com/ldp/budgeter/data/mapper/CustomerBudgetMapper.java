package com.ldp.budgeter.data.mapper;

import com.ldp.budgeter.model.CustomerBudget;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBudgetMapper implements RowMapper<CustomerBudget> {

    public CustomerBudget mapRow(ResultSet rs, int i) throws SQLException {
        CustomerBudget customerBudget = new CustomerBudget();
        customerBudget.setCustomerId(rs.getInt("customer_id"));
        customerBudget.setBudgetId(rs.getInt("budget_id"));
        customerBudget.setAccurateBal(rs.getFloat("accurate_balance"));
        customerBudget.setUserDefBal(rs.getFloat("user_def_balance"));
        return customerBudget;
    }

}
