package com.example.expenseapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expenseapp.R;
import com.example.expenseapp.TransactionDBHandler;
import com.example.expenseapp.incomes.IncomeTransaction;
import com.example.expenseapp.incomes.IncomeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddIncome extends Fragment {

    private TextView dateLabel,incomeTypeLabel;
    private EditText amountInput, descriptionInput;
    private Spinner spinner;
    private Button dateButton, submitButton;
    private LocalDate selectedDate;
    private IncomeType selectedIncomeType;




    public AddIncome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        dateLabel = view.findViewById(R.id.dateLabel);
        incomeTypeLabel = view.findViewById(R.id.incomeTypeLabel);
        amountInput = view.findViewById(R.id.amountInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);
        spinner = view.findViewById(R.id.incomeTypeSpinner);
        dateButton = view.findViewById(R.id.datePickerButton);
        submitButton = view.findViewById(R.id.submitButton);

        // Populate the spinner with IncomeType values
        IncomeType[] incomeTypes = IncomeType.values();
        List<String> incomeTypeList = new ArrayList<>();
        for (IncomeType type : incomeTypes) {
            incomeTypeList.add(type.name());
        }

        ArrayAdapter<IncomeType> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                IncomeType.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Append the selected income type to the incomeTypeLabel
                //String selectedType = parent.getItemAtPosition(position).toString();
                selectedIncomeType = (IncomeType) parent.getItemAtPosition(position);
                incomeTypeLabel.setText("Selected Income Type: " + selectedIncomeType.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Code for if nothing is selected. Will be handled elsewhere
            }
        });

        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        // Add the selected date to the incomeTypeLabel
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateLabel.setText("Date: " + selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        submitButton.setOnClickListener(v -> {
            if (descriptionInput.getText().toString().trim().isEmpty()){
                Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (amountInput.getText().toString().trim().isEmpty()){
                Toast.makeText(getContext(), "Amount cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dateLabel.getText().toString().trim().equals("Date:")){ //empty Date
                Toast.makeText(getContext(), "Date cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (spinner.getSelectedItem() == null || spinner.getSelectedItem().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "Please select an income type", Toast.LENGTH_SHORT).show();
                return;
            }

            //if all are filled submit to transaction_db
            TransactionDBHandler transactionDBHandler = new TransactionDBHandler(getContext());

            transactionDBHandler.addIncome(descriptionInput.toString(),
                    Double.parseDouble(amountInput.toString()),
                    selectedDate.toString(),
                    selectedIncomeType.hashCode());
            //TODO what values to parse? And align with the TransactionDBHandler and incomes folder

            Toast.makeText(getContext(), "Income succusfully added", Toast.LENGTH_SHORT).show();
            return;


        });
        return view;
    }
}