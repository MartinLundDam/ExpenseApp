package com.example.expenseapp;

import java.time.LocalDate;

public abstract class TransactionItem {

    private double amount;
    private LocalDate date;
    private String description;

    public TransactionItem(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    //getters
    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    //setters
    public void setAmount(double amount) {

        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public abstract Enum<?> getIncomeType();

}
