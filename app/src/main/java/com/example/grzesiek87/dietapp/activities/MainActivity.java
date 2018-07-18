package com.example.grzesiek87.dietapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.grzesiek87.dietapp.R;

import static com.example.grzesiek87.dietapp.activities.AboutYou.USER_NAME;

public class MainActivity extends AppCompatActivity {

    TextView hello_text;

    /**
     * W metodzie onCreate() sprawdzamy czy został dodany wpis z imieniem do pamięci wewnętrznej
     * jeśli tak, to zostajemy przekierowani do aktywności HomeActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello_text = findViewById(R.id.welcomeTextView);
        SharedPreferences prefs = getSharedPreferences("pl.DietApp.username", Context.MODE_PRIVATE);
        String lanSettings = prefs.getString(USER_NAME, "Anonim");
        if(!(lanSettings.isEmpty() || lanSettings.equals("Anonim")))
        {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void nextChooseDiet(View view) {
        Intent intent = new Intent(this,ChooseDietActivity.class);
        startActivity(intent);
    }

    public void getMenuAboutProgram(MenuItem item) {
        Intent intent = new Intent(this,AboutApplication.class);
        startActivity(intent);
    }
}
