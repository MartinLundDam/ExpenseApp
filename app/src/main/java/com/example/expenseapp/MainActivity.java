package com.example.expenseapp;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;

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
import com.example.expenseapp.fragments.AddFragment;
import com.example.expenseapp.fragments.BudgetFragment;
import com.example.expenseapp.fragments.HomeFragment;
import com.example.expenseapp.fragments.ProfileFragment;
import com.example.expenseapp.fragments.TransactionsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHandler dbHandler = new DBHandler(this);
        dbHandler.onOpen(dbHandler.getWritableDatabase()); //open database
        dbHandler.onUpgrade(dbHandler.getWritableDatabase()); //clear and start database again
        //Add users
        dbHandler.addUser("Martin", "martin@dk", "123");
        dbHandler.addUser("Theresa", "theresa@mail.dk","321");

        replaceFragment(new HomeFragment()); //make log-in page the standard

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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

}