package com.ldp.budgeter.data.mapper;

import com.ldp.budgeter.model.Budget;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetMapper implements RowMapper<Budget>{

    public Budget mapRow(ResultSet rs, int i) throws SQLException {
        Budget budget = new Budget();
        budget.setBudgetId(rs.getInt("id"));
        budget.setBudgetName(rs.getString("budget_name"));
        return budget;
    }
}
