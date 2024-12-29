package com.example.expenseapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expenseapp.R;
import com.example.expenseapp.TransactionDBHandler;
import com.example.expenseapp.incomes.IncomeTransaction;

import java.util.List;

public class TransactionsFragment extends Fragment {

    private TextView tvIncome1, tvIncome2; // TextViews for displaying the incomes

    public TransactionsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        // Initialize the TextViews
        tvIncome1 = view.findViewById(R.id.tv_income1);
        tvIncome2 = view.findViewById(R.id.tv_income2);

        // Get the incomes from the database
        TransactionDBHandler dbHandler = new TransactionDBHandler(getContext());
        List<IncomeTransaction> incomeList = dbHandler.getAllIncomes();

        // Display the incomes in TextViews
        if (incomeList.size() > 0) {
            IncomeTransaction firstIncome = incomeList.get(0);
            tvIncome1.setText("Description: " + firstIncome.getDescription() +
                    "\nAmount: " + firstIncome.getAmount() +
                    "\nDate: " + firstIncome.getDate() +
                    "\nType: " + firstIncome.getIncomeType());

            if (incomeList.size() > 1) {
                IncomeTransaction secondIncome = incomeList.get(1);
                tvIncome2.setText("Description: " + secondIncome.getDescription() +
                        "\nAmount: " + secondIncome.getAmount() +
                        "\nDate: " + secondIncome.getDate() +
                        "\nType: " + secondIncome.getIncomeType());
            }
        }

        return view;
    }
}