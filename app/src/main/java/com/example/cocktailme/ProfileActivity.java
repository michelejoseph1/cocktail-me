package com.example.cocktailme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity implements Config {

    EditText user_input, email, fname, lname;
    String user, email1, fname1, lname1, reply, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_input = findViewById(R.id.user1);
        email = findViewById(R.id.email1);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        user = "";
        email1 = "";
        fname1 = "";
        lname1 = "";
    }

    public void create(View view) {
        user = user_input.getText().toString();
        email1 = email.getText().toString();
        fname1 = fname.getText().toString();
        lname1 = lname.getText().toString();

        if(user.isEmpty() || email1.isEmpty() || fname1.isEmpty() || lname1.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "Fields cannot be blank", Toast.LENGTH_SHORT).show();  // Check whether the fields are not blank
        }
        else {
            Toast failed_toast = Toast.makeText(ProfileActivity.this, "Request failed", Toast.LENGTH_SHORT);
            Toast created_toast = Toast.makeText(ProfileActivity.this, "User created", Toast.LENGTH_SHORT);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL (Config.api_url2);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json; utf-8");
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject input = new JSONObject();
                        // Give data to the json object
                        input.put("username", user);
                        input.put("email", email1);
                        input.put("firstName", fname1);
                        input.put("lastName", lname1);
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        os.writeBytes(input.toString());
                        os.flush();
                        os.close();
                        InputStream inputStream = conn.getInputStream();
                        StringBuffer stringBuffer= new StringBuffer();
                        try {
                            int chr;
                            while ((chr = inputStream.read()) != -1) {
                                stringBuffer.append((char) chr);
                            }
                            reply = stringBuffer.toString();
                        } finally {
                            inputStream.close();
                        }
                        code = String.valueOf(conn.getResponseCode());
                        conn.disconnect();
                        Log.i("Code", code);
                        // For unreachable network or other network related failures.
                        if (!code.equals("201")) {
                            failed_toast.show();
                        }
                        else {
                            created_toast.show();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        failed_toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        failed_toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        failed_toast.show();
                    }
                }
            });
            thread.start();
        }
    }

    public void user(View view) {
        Intent user = new Intent(ProfileActivity.this, User.class);
        startActivity(user);
    }
}