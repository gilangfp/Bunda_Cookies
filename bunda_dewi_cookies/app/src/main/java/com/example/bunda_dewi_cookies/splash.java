package com.example.bunda_dewi_cookies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {
    private int waktu_loading=4000;
    //pindah 4detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home= new Intent(splash.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },waktu_loading);
    }
    }

