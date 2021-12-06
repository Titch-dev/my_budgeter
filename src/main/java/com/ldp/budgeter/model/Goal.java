package com.ldp.budgeter.model;

import java.time.LocalDateTime;

public class Goal {

    private int goalId;
    private String goalName;
    private float goalBal;
    private float targetAmount;
    private LocalDateTime targetDate;
    private boolean achieved;
    private int customerId;


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

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isAchieved() {
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
