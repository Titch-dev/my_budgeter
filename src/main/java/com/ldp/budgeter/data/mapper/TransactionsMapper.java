package com.ldp.budgeter.data.mapper;

import com.ldp.budgeter.model.Transactions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionsMapper implements RowMapper<Transactions> {

    public Transactions mapRow(ResultSet resultSet, int i) throws SQLException {
        Transactions transaction = new Transactions();
        transaction.setTransId(resultSet.getInt("id"));
        transaction.setDirection(resultSet.getString("direction"));
        transaction.setBudgetId(resultSet.getInt("budget_id"));
        if (resultSet.getString("sub_category") != null){
            transaction.setSubCategory(resultSet.getString("sub_category"));
        };
        transaction.setTransDate(resultSet.getDate("trans_date"));
        if(resultSet.getDate("end_date") !=null){
            transaction.setEndDate(resultSet.getDate("end_date"));
        };
        transaction.setTransAmount(resultSet.getFloat("trans_amount"));
        transaction.setFrequency(resultSet.getString("frequency"));
        transaction.setCustomerId(resultSet.getInt("customer_id"));
        return transaction;
    }

}
