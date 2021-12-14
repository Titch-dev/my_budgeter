package com.ldp.budgeter.data.mapper;

import com.ldp.budgeter.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getInt("id"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setEmail(resultSet.getString("email"));
        customer.setBalName(resultSet.getString("bal_name"));
        customer.setBalStart(resultSet.getDate("bal_start"));
        customer.setCurrentBal(resultSet.getFloat("current_bal"));
        customer.setCurrentIn(resultSet.getFloat("current_in"));
        customer.setCurrentOut(resultSet.getFloat("current_out"));
        customer.setBalGoal(resultSet.getFloat("bal_goal"));
        customer.setBalSavings(resultSet.getFloat("bal_savings"));
        return customer;
    }
}
