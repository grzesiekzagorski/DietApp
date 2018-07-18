package com.example.grzesiek87.dietapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;
import com.example.grzesiek87.dietapp.plan.Product;
import com.example.grzesiek87.dietapp.plan.ProductViewModel;

/**
 * Aktywność odpowiedzialna za dodawanie składników do bazy danych
 */
public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        Button button = findViewById(R.id.buttonDodajSkladnik);
        final TextView nazwa = findViewById(R.id.editTextNazwa);
        final TextView kalorie = findViewById(R.id.editTextKalorie);
        final TextView wegl = findViewById(R.id.editTextWeglowodany);
        final TextView bial = findViewById(R.id.editTextBialko);
        final TextView tlusz = findViewById(R.id.editTextTluszcz);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double kcal = Double.parseDouble(kalorie.getText().toString());
                Double carb = Double.parseDouble(wegl.getText().toString());
                Double prot = Double.parseDouble(bial.getText().toString());
                Double fat = Double.parseDouble(tlusz.getText().toString());

                productViewModel.insertProduct(new Product(nazwa.getText().toString(),"Kategoria",kcal,carb,prot,fat,0));
                Toast toast = Toast.makeText(getApplicationContext(),"Dodano produkt",Toast.LENGTH_LONG);
                toast.show();
                nazwa.setText("");
                kalorie.setText("");
                wegl.setText("");
                bial.setText("");
                tlusz.setText("");
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
