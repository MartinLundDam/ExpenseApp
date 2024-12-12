package com.example.expenseapp;

import java.time.LocalDate;

public abstract class TransactionItem {

    private double amount;
    private LocalDate date;

    public TransactionItem(double amount, LocalDate date) {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public abstract Enum<?> getExpenseType();

}
