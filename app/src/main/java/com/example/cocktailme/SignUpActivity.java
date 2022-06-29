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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button signUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = "";
                signUpUser(username, password);
            }
        });
    }
    private void signUpUser(String username, String password) {
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("email@example.com");
// Set custom properties
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private void goMainActivity() {
<<<<<<< HEAD
<<<<<<< HEAD
        Intent signUpToMain = new Intent(this, MainActivity.class);
        startActivity(signUpToMain);
=======
        Intent sign_up_to_main = new Intent(this, MainActivity.class);
        startActivity(sign_up_to_main);
>>>>>>> 72cc745 (fixing after PR comments)
=======
        Intent signUpToMain = new Intent(this, MainActivity.class);
        startActivity(signUpToMain);
>>>>>>> d1da0c8 (fixed variable names for java)
        finish();
    }
}


