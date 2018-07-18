package com.example.grzesiek87.dietapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.grzesiek87.dietapp.R;

import static com.example.grzesiek87.dietapp.activities.AboutYou.USER_GROWTH;
import static com.example.grzesiek87.dietapp.activities.AboutYou.USER_NAME;
import static com.example.grzesiek87.dietapp.activities.AboutYou.USER_WEIGHT;

/**
 * Klasa ta odpowiada za tworzenie menu głównego. W niej wyświetlamy również parametry wpisane przez
 * uzytkownika.
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView hello_text;
    TextView textName;
    TextView textGrowth;
    TextView textWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);


        navigationView.setNavigationItemSelectedListener(this);

        hello_text = findViewById(R.id.welcomeTextView);
        SharedPreferences prefs = getSharedPreferences("pl.DietApp.username", Context.MODE_PRIVATE);
        String lanSettings = prefs.getString(USER_NAME, "Anonim");
        hello_text.setText("Witaj "+lanSettings);
        textName = (TextView) headerView.findViewById(R.id.textNameHeaderHome);
        textName.setText(lanSettings);

        SharedPreferences sharedPrefWeight= getSharedPreferences("pl.DietApp.userweight", Context.MODE_PRIVATE);
        String weight = sharedPrefWeight.getString(USER_WEIGHT, "Anonim");
        textWeight= (TextView) headerView.findViewById(R.id.textWeightHeaderHome);
        textWeight.setText(weight+"kg");

        SharedPreferences sharedPrefGrowth= getSharedPreferences("pl.DietApp.usergrowth", Context.MODE_PRIVATE);
        String growth = sharedPrefGrowth.getString(USER_GROWTH, "Anonim");
        textGrowth= (TextView) headerView.findViewById(R.id.textGrowthHeaderHome);
        textGrowth.setText(growth+"cm");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    public void getMenuAboutProgram(MenuItem item) {
        Intent intent = new Intent(this,AboutApplication.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.plan_zywieniowy) {
            Intent intent = new Intent(this,PlanActivity.class);
            startActivity(intent);

        } else if (id == R.id.baza_produktow) {
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);

        } else if (id == R.id.skanuj_produkt) {
            Intent intent = new Intent(this,ScannerQrActivity.class);
            startActivity(intent);

        } else if (id == R.id.wskaznik_wagi) {
            Intent intent = new Intent(this,BmiCalculator.class);
            startActivity(intent);
        }
        else if (id == R.id.zeskanowane_produkty) {
            Intent intent = new Intent(this,QrFoodListActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
