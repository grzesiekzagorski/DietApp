package com.example.grzesiek87.dietapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.grzesiek87.dietapp.QrScanFood.QrFood;
import com.example.grzesiek87.dietapp.QrScanFood.QrFoodAdapter;
import com.example.grzesiek87.dietapp.QrScanFood.QrFoodViewModel;
import com.example.grzesiek87.dietapp.R;

import java.util.List;

/**
 * Klasa ta zawiera adapter umozliwiajacy wyświetlenie zeskanowanych produktów w trybie listy.
 * Metoda onSwiped() umożliwia usunięcie zeskanowanego produktu z listy produktów.
 */
public class QrFoodListActivity extends AppCompatActivity {

    private QrFoodAdapter mAdapter;
    QrFoodViewModel qrFoodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_food_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Deklaracja i inicjalizacja qrFoodViewModel
        qrFoodViewModel = ViewModelProviders.of(this).get(QrFoodViewModel.class);

        // znajdź RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.qr_recycler);

        // ustawienie sposobu rozmieszczenia elementów
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // utworzenie adaptera
        mAdapter = new QrFoodAdapter();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                QrFood qrFood = mAdapter.getQrFood(swipedPosition);
                qrFoodViewModel.delete(qrFood);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // połączenie adaptera z RecyclerView
        recyclerView.setAdapter(mAdapter);
        // Create the observer which updates the UI.
        final Observer<List<QrFood>> nameObserver = new Observer<List<QrFood>>() {
            @Override
            public void onChanged(@Nullable List<QrFood> reviews) {
                mAdapter.setReviews(reviews);
            }
        };


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        qrFoodViewModel.getAllQrFoods().observe(this,nameObserver);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}



