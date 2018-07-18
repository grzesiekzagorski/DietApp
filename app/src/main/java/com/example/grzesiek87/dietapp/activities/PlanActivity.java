package com.example.grzesiek87.dietapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.grzesiek87.dietapp.R;
import com.example.grzesiek87.dietapp.plan.DietAppDatabase;
import com.example.grzesiek87.dietapp.plan.Dish;
import com.example.grzesiek87.dietapp.plan.DishDao;
import com.example.grzesiek87.dietapp.plan.DishProductJoin;
import com.example.grzesiek87.dietapp.plan.DishProductJoinDao;
import com.example.grzesiek87.dietapp.plan.DishProductJoinViewModel;
import com.example.grzesiek87.dietapp.plan.Product;
import com.example.grzesiek87.dietapp.plan.ProductDao;
import com.example.grzesiek87.dietapp.plan.ProductViewModel;
import com.example.grzesiek87.dietapp.plan.SlideAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Aktywność odpowiedzialna za możliwość planowania posiłków
 */
public class PlanActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;

    private DishProductJoinViewModel dishProductJoinViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        dishProductJoinViewModel = ViewModelProviders.of(this).get(DishProductJoinViewModel.class);
        final android.arch.lifecycle.Observer<List<Product>> observer = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                myAdapter.setProdukty(0,products);
                List list = new ArrayList<String>();
                for(int i = 0 ; i < 0 ; i++)
                    list.add(i,products.get(i).getCalories());
                myAdapter.strings.add(0,list);
                myAdapter.notifyDataSetChanged();
            }
        };
        dishProductJoinViewModel.getAllProductsFromDish(1).observe(this, observer);


        //Utworzenie obserwatorów, którzy będą aktualizować viewpagera
        final android.arch.lifecycle.Observer<List<Product>> observer2 = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                myAdapter.setProdukty(1,products);
                myAdapter.notifyDataSetChanged();
                viewPager.setAdapter(myAdapter);
            }
        };
        dishProductJoinViewModel.getAllProductsFromDish(2).observe(this, observer2);

        final android.arch.lifecycle.Observer<List<Product>> observer3 = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                myAdapter.setProdukty(2,products);
                myAdapter.notifyDataSetChanged();
                viewPager.setAdapter(myAdapter);
            }
        };
        dishProductJoinViewModel.getAllProductsFromDish(3).observe(this, observer3);

        final android.arch.lifecycle.Observer<List<Product>> observer4 = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                myAdapter.setProdukty(3,products);
                myAdapter.notifyDataSetChanged();
                viewPager.setAdapter(myAdapter);
            }
        };
        dishProductJoinViewModel.getAllProductsFromDish(4).observe(this, observer4);

        final android.arch.lifecycle.Observer<List<Product>> observer5 = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                myAdapter.setProdukty(4,products);
                myAdapter.notifyDataSetChanged();
                viewPager.setAdapter(myAdapter);
            }
        };
        dishProductJoinViewModel.getAllProductsFromDish(5).observe(this, observer5);


            myAdapter = new SlideAdapter(this);
            viewPager.setAdapter(myAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
