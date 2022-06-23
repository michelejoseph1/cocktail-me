package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cocktailme.R;
import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity {

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
<<<<<<< HEAD
<<<<<<< HEAD
                Intent settingsToLogin = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(settingsToLogin);
=======
                Intent settings_to_login = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(settings_to_login);
>>>>>>> 72cc745 (fixing after PR comments)
=======
                Intent settingsToLogin = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(settingsToLogin);
>>>>>>> d1da0c8 (fixed variable names for java)
            }
        });
    }
}