package com.example.expenseapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.expenseapp.R;
import com.google.android.material.animation.AnimatableView;

import org.w3c.dom.Text;


public class BudgetFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private SeekBar startCapitalSeekBar, monthlyCapitalSeekBar, interestRateSeekBar, timeHorizonSeekBar;
    private TextView startCapitalView, monthlyCapitalView, interestRateView, timeHorizonView, amountTextView;

    public BudgetFragment() {
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
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        //SeekBars
        startCapitalSeekBar = view.findViewById(R.id.startCapitalSB);
        monthlyCapitalSeekBar = view.findViewById(R.id.monthlyCapitalSB);
        interestRateSeekBar = view.findViewById(R.id.interestRateSB);
        timeHorizonSeekBar = view.findViewById(R.id.timeHorizonSB);

        //textViews
        startCapitalView = view.findViewById(R.id.startCapitalTV);
        monthlyCapitalView = view.findViewById(R.id.monthlyCapitalTV);
        interestRateView = view.findViewById(R.id.interestRateTV);
        timeHorizonView = view.findViewById(R.id.timeHorizonTV);
        amountTextView = view.findViewById(R.id.amountTV);

        //On-listeners
        startCapitalSeekBar.setOnSeekBarChangeListener(this);
        monthlyCapitalSeekBar.setOnSeekBarChangeListener(this);
        interestRateSeekBar.setOnSeekBarChangeListener(this);
        timeHorizonSeekBar.setOnSeekBarChangeListener(this);

        return view;
    }

    private void updatePotentialAmount() {
        double amount;
        int startCapital = startCapitalSeekBar.getProgress();
        int monthlyCapital = monthlyCapitalSeekBar.getProgress();
        int interestRate = interestRateSeekBar.getProgress();
        int timeHorizon = timeHorizonSeekBar.getProgress();

        int weightedStartCapital = startCapital * 1000;
        int weightedMonthlyCapital = monthlyCapital * 50;

        //interest rate to decimal
        double annualInterestRate = interestRate / 100.0;

        //montly compound
        int n = 12;

        if (interestRate > 0) {
            amount = weightedStartCapital * Math.pow((1 + annualInterestRate / n), n * timeHorizon)
                    + weightedMonthlyCapital * (Math.pow((1 + annualInterestRate / n), n * timeHorizon) - 1) / (annualInterestRate / n);
            amountTextView.setText("Your potential capital: " + (int) amount);
        } else if (interestRate <= 0) {
            amount = weightedStartCapital + weightedMonthlyCapital * n * timeHorizon;
            amountTextView.setText("Your potential capital: " + (int) amount);
        }


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) return; //if the user dident do anything, ignore.

        if (seekBar.getId() == R.id.startCapitalSB) {
            //TODO do it go in 1000 per tick.
            int weightedStartCapital = progress * 1000;
            startCapitalView.setText("Start Capital: " + weightedStartCapital + " MYR");
        } else if (seekBar.getId() == R.id.monthlyCapitalSB) {
            int weightedMonthlyCapital = progress * 50;
            monthlyCapitalView.setText("Monthly capital: " + weightedMonthlyCapital + " MYR");
        } else if (seekBar.getId() == R.id.interestRateSB) {
            interestRateView.setText("Interest rate: " + progress + " %");
        } else if (seekBar.getId() == R.id.timeHorizonSB) {
            timeHorizonView.setText("Time horizon: " + progress + " Years");
        }

        updatePotentialAmount();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }


}