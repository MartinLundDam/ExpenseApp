package com.example.expenseapp;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.expenseapp.R;


import com.example.expenseapp.databinding.ActivityMainBinding;
import com.example.expenseapp.expenses.ExpenseType;
import com.example.expenseapp.fragments.AddFragment;
import com.example.expenseapp.fragments.BudgetFragment;
import com.example.expenseapp.fragments.HomeFragment;
import com.example.expenseapp.fragments.LoginFragment;
import com.example.expenseapp.fragments.ProfileFragment;
import com.example.expenseapp.fragments.TransactionsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize DBHandler
        DBHandler dbHandler = new DBHandler(this);
        SQLiteDatabase sqLiteDatabase = dbHandler.getWritableDatabase();  // Triggers onUpgrade if version changes

        // Add users or other operations after the database is upgraded
        dbHandler.addUser("User1", "email1@example.com", "123");
        dbHandler.addUser("User2", "email2@example.com", "321");

        // Initialize TransactionDBHandler and add income
        TransactionDBHandler tdb = new TransactionDBHandler(this);
        SQLiteDatabase db = tdb.getWritableDatabase();
        tdb.addIncome("løn", 12, "12", 1);

        replaceFragment(new LoginFragment()); //make log-in page the first fragment to show

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.button_home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.button_add) {
                replaceFragment(new AddFragment());
            } else if (id == R.id.button_transactions) {
                replaceFragment(new TransactionsFragment());
            } else if (id == R.id.button_budget) {
                replaceFragment(new BudgetFragment());
            } else if (id == R.id.button_profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });

    }


    public void replaceFragment(Fragment fragment) {

        if (fragment instanceof LoginFragment) {
            binding.bottomNavigationView.setVisibility(View.GONE); // Hide navbar
        } else {
            binding.bottomNavigationView.setVisibility(View.VISIBLE); // Show navbar
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }


}