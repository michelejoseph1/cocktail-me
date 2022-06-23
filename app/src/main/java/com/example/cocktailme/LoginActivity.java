package com.example.cocktailme;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailme.R;
import com.parse.LogInCallback;
import com.parse.ParseUser;

import java.text.ParseException;

    public class LoginActivity extends AppCompatActivity {
        public static final String TAG = "LoginActivity";
        private EditText etUsername, etPassword;
        private Button btnLogin, btnSignUp;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            if(ParseUser.getCurrentUser() != null) {
                goMainActivity();
            }
            btnSignUp = findViewById(R.id.btnSignUp);
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goSignUp();
                }
            });

            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick login button");
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    loginUser(username, password);
                }
            });
        }



        private void loginUser(String username, String password) {
            Log.i(TAG, "attempting to login user " + username);
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with login",e);
                        Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.d(TAG, "login worked");

                    goMainActivity();
                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void goMainActivity() {
            Intent login_to_main = new Intent(this, MainActivity.class);
            startActivity(login_to_main);
            finish();
        }
        private void goSignUp() {
            Intent login_to_sign_up = new Intent(this, SignUpActivity.class);
            startActivity(login_to_sign_up);
            finish();
        }
    }