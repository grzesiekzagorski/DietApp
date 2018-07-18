package com.example.grzesiek87.dietapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;

public class AboutYou extends AppCompatActivity {

    EditText name;
    EditText growth;
    EditText weight;
    Spinner sexSpinner;
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_GROWTH="USER_GROWTH";
    public static final String USER_WEIGHT="USER_WEIGHT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Metoda obsługująca przycisk dalej, umożliwiający otwarcie kolejnej aktywności
     * zawiera zabezpieczenia przed wpisaniem niekompletnych oraz niepoprawnych danych od
     * użytkownika
     * @param view
     */
    public void nextHomeActivity(View view) {

        name = findViewById(R.id.textName);
        growth = findViewById(R.id.textGrowth);
        weight = findViewById(R.id.textWeight);
        sexSpinner = findViewById(R.id.spinnerSex);
        String text = sexSpinner.getSelectedItem().toString();


        if(name.getText().toString().isEmpty() || growth.getText().toString().isEmpty() ||
                weight.getText().toString().isEmpty() || text.equals("pusty"))
        {
            Toast.makeText(this,"Niekompletne dane",Toast.LENGTH_SHORT).show();
        }
        else if(!((AboutYou.returnFlag(growth.getText().toString()))
                &&(AboutYou.returnFlag(weight.getText().toString()))))
        {
            Toast.makeText(this,"Niepoprawne dane",Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences sharedPref = getSharedPreferences("pl.DietApp.username", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USER_NAME,name.getText().toString());
            editor.apply();
            finish();
            SharedPreferences sharedPrefWeight= getSharedPreferences("pl.DietApp.userweight", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorWeight = sharedPrefWeight.edit();
            editorWeight.putString(USER_WEIGHT,weight.getText().toString());
            editorWeight.apply();
            finish();
            SharedPreferences sharedPrefGrowth= getSharedPreferences("pl.DietApp.usergrowth", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorGrowth = sharedPrefGrowth.edit();
            editorGrowth.putString(USER_GROWTH,growth.getText().toString());
            editorGrowth.apply();
            finish();
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Metoda ta sprawdza czy użytkownik podał poprawne dane przy wpisywaniu swojego wieku
     * @param result jest odpowiednikiem parametru wiek podawanym przez uzytkownika w EditText
     * @return zwraca flagę, jeśli ma wartość false tzn. ze został popełniony błąd.
     */
    public static boolean returnFlag(String result) {
        boolean wzrost = true;
        if (!(result.length() < 2 || result.length() > 3)){
            char znak;
            int tablica[] = new int[result.length()];
            int kod_ASCII;
            int j = 0;
            for (int i = 0; i < tablica.length; i++) {
                znak = result.charAt(i);
                kod_ASCII = (int) znak;
                if (kod_ASCII < 48 || kod_ASCII > 57) {
                    tablica[j] = kod_ASCII;
                    j++;
                    i++;
                }
                i++;
            }
            for(int i=0;i<tablica.length;i++)
            {
                if(tablica[i] != 0)
                {
                    wzrost = false;
                }
            }
        }
        else {
            wzrost = false;
        }
        return wzrost;
    }
}
