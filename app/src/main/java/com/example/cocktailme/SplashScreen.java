package com.example.cocktailme;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailme.R;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
<<<<<<< HEAD
                Intent splashToLogin = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(splashToLogin);
=======
                Intent here_to_login = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(here_to_login);
>>>>>>> 72cc745 (fixing after PR comments)
                finish();
            }
        }, 2000);

    }
}