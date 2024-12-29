package com.example.expenseapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenseapp.MainActivity;
import com.example.expenseapp.R;

public class LoginFragment extends Fragment {

    EditText username;
    EditText password;
    Button loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);

        // Set up button click listener
        loginButton.setOnClickListener(v -> {
            if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                // Use MainActivity's replaceFragment method to navigate
                ((MainActivity) requireActivity()).replaceFragment(new HomeFragment());

            } else {
                Toast.makeText(requireContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

