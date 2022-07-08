package com.example.cocktailme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cocktailme.LoginActivity;
import com.example.cocktailme.R;
import com.parse.ParseUser;

public class SettingsFragment extends Fragment {

    public static final String TAG = "SettingsFragment";
    Button btnLogout;

    public SettingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "in the onClick");
                ParseUser.logOut();
                Intent settingsToLogin = new Intent(getContext(), LoginActivity.class);
                startActivity(settingsToLogin);
            }
        });
    }
}