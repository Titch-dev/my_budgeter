package com.ldp.budgeter.model;

import java.time.LocalDateTime;

public class Customer {

    private int customerId;
    private String firstName;
    private String email;
    private String balName;
    private LocalDateTime balStart;
    private float currentBal;
    private float currentIn;
    private float currentOut;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalName() {
        return balName;
    }

    public void setBalName(String balName) {
        this.balName = balName;
    }

    public LocalDateTime getBalStart() {
        return balStart;
    }

    public void setBalStart(LocalDateTime balStart) {
        this.balStart = balStart;
    }

    public float getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(float currentBal) {
        this.currentBal = currentBal;
    }

    public float getCurrentIn() {
        return currentIn;
    }

    public void setCurrentIn(float currentIn) {
        this.currentIn = currentIn;
    }

    public float getCurrentOut() {
        return currentOut;
    }

    public void setCurrentOut(float currentOut) {
        this.currentOut = currentOut;
    }

    public float getBalGoal() {
        return balGoal;
    }

    public void setBalGoal(float balGoal) {
        this.balGoal = balGoal;
    }

    public float getBalSavings() {
        return balSavings;
    }

    public void setBalSavings(float balSavings) {
        this.balSavings = balSavings;
    }

    private float balGoal;
    private float balSavings;



}
