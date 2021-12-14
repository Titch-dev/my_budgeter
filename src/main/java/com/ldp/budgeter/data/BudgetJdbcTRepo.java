package com.ldp.budgeter.data;

import com.ldp.budgeter.data.mapper.BudgetMapper;
import com.ldp.budgeter.model.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class BudgetJdbcTRepo implements BudgetRepo {


    private final JdbcTemplate jdbcTemplate;

    public BudgetJdbcTRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Budget findById(int budgetId) {

        final String sql = "select id, budget_name from budget " +
                            "where id = ?;";

        return jdbcTemplate.query(sql, new BudgetMapper(), budgetId)
                .stream()
                .findFirst().
                orElse(null);
    }

    @Override
    public Budget add(Budget budget) {

        final String sql = "insert into budget (budget_name) " +
                "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, budget.getBudgetName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        budget.setBudgetId(keyHolder.getKey().intValue());
        return budget;
    }

    @Override
    public Boolean deleteById(int budgetId) {

        final String sql = "delete from budget where id = ?;";

        return jdbcTemplate.update(sql, budgetId) > 0;
    }

}
