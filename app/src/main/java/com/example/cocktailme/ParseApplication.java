package com.example.cocktailme;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Rating.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fzX0n0ank9aay4OT214a1ULWhXQpxTm0cwbtEUUM")
                .clientKey("7ydoiSJw3lE9l2JJaeMT3IpsUgRH130RL5dm4nb8")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
