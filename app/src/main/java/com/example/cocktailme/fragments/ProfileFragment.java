package com.example.cocktailme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cocktailme.Config;
import com.example.cocktailme.R;
import com.example.cocktailme.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    EditText user_input, email, fname, lname;
    String user, email1, fname1, lname1, reply, code;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);
        user_input = view.findViewById(R.id.user);
        email = view.findViewById(R.id.email1);
        fname = view.findViewById(R.id.fname);
        lname = view.findViewById(R.id.lname);
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
        }
        else {
            // Create various messages to display in the app.
            Toast failed_toast = Toast.makeText(getContext(), "Request failed", Toast.LENGTH_SHORT);
            Toast created_toast = Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT);
            // Create a worker thread for sending HTTP requests.
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL (Config.ADD_URL);                                             // new url object is created
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();              // HTTP connection object is created
                        conn.setRequestMethod("POST");                                                  // POST method
                        conn.setRequestProperty("Content-Type", "application/json; utf-8");             // JSON format is specified
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject input = new JSONObject();                                           // New JSON object is created
                        // Give data to the json object
                        input.put("username", user);
                        input.put("email", email1);
                        input.put("firstName", fname1);
                        input.put("lastName", lname1);
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());             // Output stream object for HTTP connection is created
                        os.writeBytes(input.toString());                                                // JSON object is serialized and sent over the HTTP connection to the listening server
                        os.flush();                                                                     // Flushing the output buffers
                        os.close();                                                                     // Closing the output stream
                        InputStream is = conn.getInputStream();                                         // Input stream object for HTTP connection is created
                        StringBuffer stringBuffer = new StringBuffer();                                           // String buffer object is created
                        // Fetch and append the incoming bytes until no more comes over the input stream.
                        try {
                            int chr;
                            while ((chr = is.read()) != -1) {
                                stringBuffer.append((char) chr);
                            }
                            reply = stringBuffer.toString();
                        } finally {
                            is.close();                                                                 // Closing the input stream
                        }
                        code = String.valueOf(conn.getResponseCode());                                  // Get the HTTP status code
                        conn.disconnect();                                                              // Disconnecting
                        Log.i("Code", code);
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
        Intent user = new Intent(getContext(), User.class);
        startActivity(user);
    }
}