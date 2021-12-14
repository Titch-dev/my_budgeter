package com.ldp.budgeter.model;

public class CustomerBudget {

    private int customerId;
    private int budgetId;
    private float accurateBal;
    private float userDefBal;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public float getAccurateBal() {
        return accurateBal;
    }

    public void setAccurateBal(float accurateBal) {
        this.accurateBal = accurateBal;
    }

    public float getUserDefBal() {
        return userDefBal;
    }

    public void setUserDefBal(float userDefBal) {
        this.userDefBal = userDefBal;
    }
}
