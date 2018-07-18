package com.example.grzesiek87.dietapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.grzesiek87.dietapp.R;

public class AboutApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    /**
     * Metoda umożliwiająca powrót do poprzedniej aktywnośći
     */
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
