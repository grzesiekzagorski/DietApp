package com.example.grzesiek87.dietapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.grzesiek87.dietapp.R;

public class InformationAboutBMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_about_bmi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
