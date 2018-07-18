package com.example.grzesiek87.dietapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;

public class BmiCalculator extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText weight;
    EditText growth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        radioGroup = findViewById(R.id.radioGroup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bmi_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Zaznaczenie oraz wyswietleniu toastu z informacja ktory Button wybralismy
     * @param view
     */
    public void checkButton(View view) {
        int radio_ID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radio_ID);
        Toast.makeText(this,radioButton.getText(),Toast.LENGTH_SHORT).show();
    }

    /**
     * Metoda ta służy do obliczenia wskaznika BMI na podstawie danych przekazanych przez uzytkownika,
     * sprawdza równiżczy zostały podane wszystkie dane oraz czy są one poprawne
     * @param view
     */
    public void countBMI(View view) {
        growth = findViewById(R.id.textBMIGrowth);
        weight = findViewById(R.id.textBMIWeight);
        if(growth.getText().toString().isEmpty() || weight.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Niekompletne dane",Toast.LENGTH_SHORT).show();
        }
        else if(!((AboutYou.returnFlag(growth.getText().toString()))
                &&(AboutYou.returnFlag(weight.getText().toString()))))
        {
            Toast.makeText(this,"Niepoprawne dane",Toast.LENGTH_SHORT).show();
        }else
        {
            java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");
            String growth_string = growth.getText().toString();
            String weight_string = weight.getText().toString();
            double BMI = Double.parseDouble(weight_string)/ ((Double.parseDouble(growth_string)/100) * (Double.parseDouble(growth_string)/100));
            Toast.makeText(this,"Twoje BMI wynosi: "+df.format(BMI),Toast.LENGTH_SHORT).show();
        }

    }

    public void getMenuAboutBMI(MenuItem item) {
        Intent intent = new Intent(this,InformationAboutBMI.class);
        startActivity(intent);
    }
}
