package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity implements Config {

    EditText user, email, fname, lname;
    String user1, email1, fname1, lname1, reply, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = findViewById(R.id.user1);
        email = findViewById(R.id.email1);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        user1 = "";
        email1 = "";
        fname1 = "";
        lname1 = "";
    }

    public void create(View view) {
        user1 = user.getText().toString();
        email1 = email.getText().toString();
        fname1 = fname.getText().toString();
        lname1 = lname.getText().toString();

        if(user1.isEmpty() || email1.isEmpty() || fname1.isEmpty() || lname1.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "Fields cannot be blank", Toast.LENGTH_SHORT).show();  // Check whether the fields are not blank
        }
        else {
            // Create various messages to display in the app.
            Toast failed_toast = Toast.makeText(ProfileActivity.this, "Request failed", Toast.LENGTH_SHORT);
            Toast created_toast = Toast.makeText(ProfileActivity.this, "User created", Toast.LENGTH_SHORT);
            // Create a worker thread for sending HTTP requests.
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL (Config.api_url2);                                             // new url object is created
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();              // HTTP connection object is created
                        conn.setRequestMethod("POST");                                                  // POST method
                        conn.setRequestProperty("Content-Type", "application/json; utf-8");             // JSON format is specified
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject input = new JSONObject();                                           // New JSON object is created
                        // Give data to the json object
                        input.put("username", user1);
                        input.put("email", email1);
                        input.put("firstName", fname1);
                        input.put("lastName", lname1);
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());             // Output stream object for HTTP connection is created
                        os.writeBytes(input.toString());                                                // JSON object is serialized and sent over the HTTP connection to the listening server
                        os.flush();                                                                     // Flushing the output buffers
                        os.close();                                                                     // Closing the output stream
                        InputStream is = conn.getInputStream();                                         // Input stream object for HTTP connection is created
                        StringBuffer sb = new StringBuffer();                                           // String buffer object is created
                        // Fetch and append the incoming bytes until no more comes over the input stream.
                        try {
                            int chr;
                            while ((chr = is.read()) != -1) {
                                sb.append((char) chr);
                            }
                            reply = sb.toString();
                        } finally {
                            is.close();                                                                 // Closing the input stream
                        }
                        code = String.valueOf(conn.getResponseCode());                                  // Get the HTTP status code
                        conn.disconnect();                                                              // Disconnecting
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