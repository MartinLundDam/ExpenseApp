package com.example.expenseapp.expenses;

import com.example.expenseapp.TransactionItem;

import java.time.LocalDate;

public class ExpenseTransaction extends TransactionItem {
    private ExpenseType expenseType;

    public ExpenseTransaction(String description, double amount, LocalDate date, ExpenseType expenseType) {
        super(description, amount, date);
        this.expenseType = expenseType;
    }

    @Override
    public ExpenseType getExpenseType() {
        return expenseType;
    }
}
