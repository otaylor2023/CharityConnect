package com.femmehacks.charityconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class login2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Login2Fragment.newInstance())
                    .commitNow();
        }
    }
}