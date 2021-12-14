package com.ldp.budgeter.data;

import com.ldp.budgeter.data.mapper.CustomerBudgetMapper;
import com.ldp.budgeter.model.CustomerBudget;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerBudgetJdbcTRepo implements CustomerBudgetRepo{

    private final JdbcTemplate jdbcTemplate;

    public CustomerBudgetJdbcTRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomerBudget> findAllById(int customerId) {

        final String sql = "select customer_id, budget_id, accurate_balance, user_def_balance " +
                "from customer_budget " +
                "where customer_id = ?;";

        return jdbcTemplate.query(sql, new CustomerBudgetMapper(), customerId);
    }

    @Override
    public boolean add(CustomerBudget customerBudget) {

        final String sql = "insert into customer_budget (customer_id, budget_id, accurate_balance, user_def_balance) " +
                "values (?,?,?,?);";

        return jdbcTemplate.update(sql,
                customerBudget.getCustomerId(),
                customerBudget.getBudgetId(),
                customerBudget.getAccurateBal(),
                customerBudget.getUserDefBal()) > 0;
    }

    @Override
    public boolean update(CustomerBudget customerBudget) {

        final String sql = "update customer_budget set " +
                "accurate_balance = ?, user_def_balance = ? " +
                "where customer_id = ? and budget_id = ?;";

        return jdbcTemplate.update(sql,
                customerBudget.getAccurateBal(),
                customerBudget.getUserDefBal(),
                customerBudget.getCustomerId(),
                customerBudget.getBudgetId()) > 0;
    }

    @Override
    public boolean deleteById(int customerId, int budgetId) {

        final String sql = "delete from customer_budget where customer_id = ? and budget_id = ?;";

        return jdbcTemplate.update(sql, customerId, budgetId) > 0;

    }
}
