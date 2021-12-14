package com.ldp.budgeter.data;

import com.ldp.budgeter.data.mapper.CustomerMapper;
import com.ldp.budgeter.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CustomerJdbcTRepo implements CustomerRepo{

    private final JdbcTemplate jdbcTemplate;

    public CustomerJdbcTRepo(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Customer findById(int customerId) {

        final String sql = "select id, first_name, email, bal_name, bal_start, current_bal, current_in, current_out, bal_goal, bal_savings " +
        "from customer " +
        "where id = ?;";

        return jdbcTemplate.query(sql, new CustomerMapper(), customerId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer add(Customer customer) {

        final String sql = "insert into customer (first_name, email, bal_name, bal_start, current_bal, current_in, current_out, bal_goal, bal_savings) " +
                "values (?,?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getBalName());
            ps.setDate(4, customer.getBalStart());
            ps.setFloat(5, customer.getCurrentBal());
            ps.setFloat(6, customer.getCurrentIn());
            ps.setFloat(7, customer.getCurrentOut());
            ps.setFloat(8, customer.getBalGoal());
            ps.setFloat(9, customer.getBalSavings());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        customer.setCustomerId(keyHolder.getKey().intValue());
        return customer;
    }

    @Override
    public boolean update(Customer customer) {

        final String sql = "update customer set " +
                            "first_name = ?, email = ?, bal_name = ?, " +
                            "bal_start = ?, current_bal = ?, current_in = ?, " +
                            "current_out = ?, bal_goal = ?, bal_savings = ? " +
                            "where id = ?;";

        return jdbcTemplate.update(sql,
                customer.getFirstName(), customer.getEmail(), customer.getBalName(),
                customer.getBalStart(), customer.getCurrentBal(), customer.getCurrentIn(),
                customer.getCurrentOut(), customer.getBalGoal(), customer.getBalSavings(),
                customer.getCustomerId()) > 0;

    }

    @Override
    public boolean deleteById(int customerId) {

        final String sql = "delete from customer where id = ?;";

        return jdbcTemplate.update(sql, customerId) > 0;
    }
}
