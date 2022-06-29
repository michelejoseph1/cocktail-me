package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cocktailme.R;
import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent settingsToLogin = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(settingsToLogin);
            }
        });
    }
}