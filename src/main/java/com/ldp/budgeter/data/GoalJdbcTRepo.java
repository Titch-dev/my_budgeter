package com.ldp.budgeter.data;

import com.ldp.budgeter.data.mapper.GoalMapper;
import com.ldp.budgeter.model.Goal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class GoalJdbcTRepo implements GoalRepo{

    private final JdbcTemplate jdbcTemplate;

    public GoalJdbcTRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Goal findById(int goalId) {

        final String sql = "select id, goal_name, balance, target_amount, target_date, achieved, customer_id " +
                            "from goal " +
                            "where id = ?;";

        return jdbcTemplate.query(sql, new GoalMapper(), goalId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Goal add(Goal goal) {

        final String sql = "insert into goal (goal_name, balance, target_amount, target_date, achieved, customer_id) " +
                            "values (?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, goal.getGoalName());
            ps.setFloat(2, goal.getGoalBal());
            ps.setFloat(3, goal.getTargetAmount());
            ps.setDate(4, goal.getTargetDate());
            ps.setBoolean(5, goal.getAchieved());
            ps.setInt(6, goal.getCustomerId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        goal.setGoalId(keyHolder.getKey().intValue());
        return goal;

    }

    @Override
    public boolean update(Goal goal) {

        final String sql = "update goal set " +
                "goal_name = ?, balance = ?, target_amount = ?, " +
                "target_date = ?, achieved = ?, customer_id = ? " +
                "where id = ?;";

        return jdbcTemplate.update(sql,
                goal.getGoalName(), goal.getGoalBal(), goal.getTargetAmount(),
                goal.getTargetDate(), goal.getAchieved(), goal.getCustomerId(),
                goal.getGoalId()) > 0;
    }

    @Override
    public boolean deleteById(int goalId) {

        final String sql = "delete from goal where id = ?;";

        return jdbcTemplate.update(sql, goalId) > 0;
    }
}
