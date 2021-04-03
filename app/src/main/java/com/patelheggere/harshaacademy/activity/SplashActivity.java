package com.patelheggere.harshaacademy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.utils.Constants;
import com.patelheggere.harshaacademy.utils.SharedPrefsHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if( (SharedPrefsHelper.getInstance().get(Constants.FIRST_TIME)!=null) && (!(boolean)SharedPrefsHelper.getInstance().get(Constants.FIRST_TIME)))
                {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(SplashActivity.this, RegistrationActivity.class));
                }
            }
        }, 3000);
    }
}