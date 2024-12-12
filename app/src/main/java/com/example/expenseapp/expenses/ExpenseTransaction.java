package com.example.expenseapp.expenses;

import com.example.expenseapp.TransactionItem;

import java.time.LocalDate;

public class ExpenseTransaction extends TransactionItem {
    private ExpenseType expenseType;

    public ExpenseTransaction(double amount, LocalDate date, ExpenseType expenseType) {
        super(amount, date);
        this.expenseType = expenseType;
    }

    @Override
    public ExpenseType getExpenseType() {
        return expenseType;
    }
}
