package com.example.grzesiek87.dietapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;
import com.example.grzesiek87.dietapp.plan.AddToDishAdapter;
import com.example.grzesiek87.dietapp.plan.DishProductJoin;
import com.example.grzesiek87.dietapp.plan.DishProductViewModel;
import com.example.grzesiek87.dietapp.plan.Product;

import java.util.List;

/**
 * Aktywność odpowiedzialna za dodawanie składniku do wybranego dania.
 * Wykorzystuje BroadcastRecievera aby odebrać wybrany składnik z adaptera AddToDishAdapter
 */

public class AddToDishActivity extends AppCompatActivity {
    private DishProductViewModel dishProductViewModel;
    private RecyclerView mRecyclerView;
    private AddToDishAdapter mAdapter;
    private int dishId, productId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_dish);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
        dishId = Integer.parseInt(s);
        dishProductViewModel = ViewModelProviders.of(this).get(DishProductViewModel.class);
        mRecyclerView = findViewById(R.id.addToDishRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AddToDishAdapter();
        mRecyclerView.setAdapter(mAdapter);


        final android.arch.lifecycle.Observer<List<Product>> observer = new android.arch.lifecycle.Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                mAdapter.setmProducts(products);
                mAdapter.notifyDataSetChanged();
            }
        };
        dishProductViewModel.getAllProducts().observe(this, observer);

         BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction() != null && intent.getAction().equals("custom-message")) {
                    String itemName = intent.getStringExtra("item");
                    productId = Integer.parseInt(itemName);
                    Log.d("LocalBroadCast", "onReceive: " + itemName);
                    Toast.makeText(AddToDishActivity.this, "Dodano produkt", Toast.LENGTH_SHORT).show();
                    mAdapter.notifyDataSetChanged();
                    dishProductViewModel.insertDishProduct(new DishProductJoin(dishId,productId));
                    this.clearAbortBroadcast();
                    Intent intent1 = new Intent(getApplicationContext(), PlanActivity.class);
                    startActivity(intent1);
                    LocalBroadcastManager.getInstance(getParent()).unregisterReceiver(this);
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
