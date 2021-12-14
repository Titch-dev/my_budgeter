package com.ldp.budgeter.data.mapper;

import com.ldp.budgeter.model.Goal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalMapper implements RowMapper<Goal> {

    public Goal mapRow(ResultSet rs,  int i) throws SQLException {
        Goal goal = new Goal();
        goal.setGoalId(rs.getInt("id"));
        goal.setGoalName(rs.getString("goal_name"));
        goal.setGoalBal(rs.getFloat("balance"));
        goal.setTargetAmount(rs.getFloat("target_amount"));
        goal.setTargetDate(rs.getDate("target_date"));
        goal.setAchieved(rs.getBoolean("achieved"));
        goal.setCustomerId(rs.getInt("customer_id"));
        return goal;
    }
}
