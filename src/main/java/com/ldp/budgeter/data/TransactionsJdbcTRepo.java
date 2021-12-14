package com.ldp.budgeter.data;

import com.ldp.budgeter.data.mapper.TransactionsMapper;
import com.ldp.budgeter.model.Transactions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class TransactionsJdbcTRepo implements TransactionsRepo{

    private final JdbcTemplate jdbcTemplate;

    public TransactionsJdbcTRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transactions findById(int transId) {

        final String sql = "select id, direction, budget_id, sub_category, " +
                "trans_date, end_date, trans_amount, frequency, customer_id " +
                "from transactions " +
                "where id = ?;";

        return jdbcTemplate.query(sql, new TransactionsMapper(), transId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Transactions add(Transactions transaction) {

        final String sql = "insert into transactions (direction, budget_id, sub_category, trans_date, end_date, trans_amount, frequency, customer_id) " +
                        "values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, transaction.getDirection());
            ps.setInt(2, transaction.getBudgetId());
            ps.setString(3, transaction.getSubCategory());
            ps.setDate(4, transaction.getTransDate());
            ps.setDate(5, transaction.getEndDate());
            ps.setFloat(6, transaction.getTransAmount());
            ps.setString(7, transaction.getFrequency());
            ps.setInt(8, transaction.getCustomerId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        transaction.setTransId(keyHolder.getKey().intValue());
        return transaction;
    }

    @Override
    public boolean update(Transactions transaction) {

        final String sql = "update transactions set " +
                "direction = ?, budget_id = ?, sub_category = ?, " +
                "trans_date = ?, trans_amount = ?, frequency = ?, " +
                "customer_id = ? " +
                "where id = ?;";

        return jdbcTemplate.update(sql,
                transaction.getDirection(), transaction.getBudgetId(),
                transaction.getSubCategory(), transaction.getTransDate(),
                transaction.getTransAmount(), transaction.getFrequency(),
                transaction.getCustomerId(), transaction.getTransId()) > 0;
    }

    @Override
    public boolean deleteById(int transId) {

        final String sql = "delete from transactions where id = ?;";

        return jdbcTemplate.update(sql, transId) > 0;
    }
}
