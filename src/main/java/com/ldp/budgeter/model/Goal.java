package com.ldp.budgeter.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Goal {

    private int goalId;
    private String goalName;
    private float goalBal;
    private float targetAmount;
    private Date targetDate;
    private boolean achieved;
    private int customerId;

    public Goal(){

    }

    public Goal(int goalId, String goalName, float targetAmount, int customerId){
        this.goalId = goalId;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.customerId = customerId;
    }


    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public float getGoalBal() {
        return goalBal;
    }

    public void setGoalBal(float goalBal) {
        this.goalBal = goalBal;
    }

    public float getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
