package com.example.expenseapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expenseapp.R;

public class AddFragment extends Fragment {
    Button button;
    TextView textView;
    RadioButton addIncomeRB, addExpenseRB;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        //assign views
        button = view.findViewById(R.id.continueButton);
        textView = view.findViewById(R.id.addTV);
        addIncomeRB = view.findViewById(R.id.addIncomeRB);
        addExpenseRB = view.findViewById(R.id.addExpenseRB);

        //OnClickListener when button is clicked
        button.setOnClickListener(v -> {
            if (addIncomeRB.isChecked()) { //if addIncomeRB is clicked go to AddIncome
                navigateToTransactionAdd(new AddIncome());
            } else if (addExpenseRB.isChecked()) { //if addExpenseRB is clicked go to AddIncome
                navigateToTransactionAdd(new AddExpense());
            } else { //if none is picked make a Toast message
                Toast.makeText(requireContext(), "Please click an option", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void navigateToTransactionAdd(Fragment fragment) {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).
                addToBackStack(null).commit();
    }
}