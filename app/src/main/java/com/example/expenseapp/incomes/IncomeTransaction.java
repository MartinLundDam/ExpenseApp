package com.example.expenseapp.incomes;

import com.example.expenseapp.TransactionItem;

import java.time.LocalDate;

public class IncomeTransaction extends TransactionItem {
    private IncomeType incomeType;

    public IncomeTransaction(double amount, LocalDate date,IncomeType incomeType) {
        super(amount, date);
        this.incomeType = incomeType;
    }

    @Override
    public IncomeType getExpenseType() {
        return incomeType;
    }
}
