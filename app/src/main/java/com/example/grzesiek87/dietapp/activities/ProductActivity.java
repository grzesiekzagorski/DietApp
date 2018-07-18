package com.example.grzesiek87.dietapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;
import com.example.grzesiek87.dietapp.plan.Dish;
import com.example.grzesiek87.dietapp.plan.DishViewModel;
import com.example.grzesiek87.dietapp.plan.Product;
import com.example.grzesiek87.dietapp.plan.AddToDishAdapter;
import com.example.grzesiek87.dietapp.plan.ProductAdapter;
import com.example.grzesiek87.dietapp.plan.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Aktywność odpowiedzialna za wyświetlenie składników w bazie produktóww
 */
public class ProductActivity extends AppCompatActivity {
    private ProductAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProductViewModel productViewModel;
    private DishViewModel dishViewModel;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        button = (Button) findViewById(R.id.goToAddProductActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),AddProductActivity.class);
            startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.activityProductRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ProductAdapter();
        mRecyclerView.setAdapter(mAdapter);

        final android.arch.lifecycle.Observer<List<Product>> observer = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                if (productViewModel.getAllProducts().getValue() != null)
                    if (productViewModel.getAllProducts().getValue().size() == 0)
                        initDatabase(productViewModel,dishViewModel);
                mAdapter.setmProducts(products);
            }
        };
        productViewModel.getAllProducts().observe(this, observer);
    }


    /**
     * Funkcja inicjalizująca bazę danych
     * @param productViewModel
     * @param dishViewModel
     */
    void initDatabase(ProductViewModel productViewModel, DishViewModel dishViewModel) {
        List<Product> products = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();

        dishViewModel.insertDish(new Dish("Sniadanie"));
        dishViewModel.insertDish(new Dish("Drugie Sniadanie"));
        dishViewModel.insertDish(new Dish("Obiad"));
        dishViewModel.insertDish(new Dish("Podwieczorek"));
        dishViewModel.insertDish(new Dish("Kolacja"));


        products.add(new Product("Jabłko", "Owoce", 52, 14, 0, 0, 1.99));
        products.add(new Product("Pierś z kurczaka", "Nabiał", 110, 3, 21.5, 1.3, 12.99));
        products.add(new Product("Jajko", "Nabiał", 140, 3, 4, 5, 6));
        products.add(new Product("Papryka", "Nabiał", 40, 5, 1, 0, 6));
        products.add(new Product("Ser Żółty", "Nabiał", 352, 5, 11, 7, 6));
        products.add(new Product("Mleko", "Nabiał", 50, 3, 4, 2, 6));

        for (int i = 0; i < products.size(); i++) {
            productViewModel.insertProduct(products.get(i));
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
