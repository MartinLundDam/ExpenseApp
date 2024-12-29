package com.example.expenseapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.expenseapp.MainActivity;
import com.example.expenseapp.R;

public class ProfileFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the button using its ID
        View logoutButton = view.findViewById(R.id.profile_button5);

        // Set an OnClickListener to handle the logout action
        logoutButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
            // Use MainActivity's replaceFragment method to navigate
            ((MainActivity) requireActivity()).replaceFragment(new LoginFragment());
        });

        return view;
    }
}
