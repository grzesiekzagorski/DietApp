package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Repozytorium klasy DishProductJoin
 */
public class DishProductJoinRepository {
    private DishProductJoinDao dishProductJoinDao;
    private LiveData<List<Product>> listLiveData;

    DishProductJoinRepository(@NonNull Application application) {
        DietAppDatabase db = DietAppDatabase.getDatabase(application);
        dishProductJoinDao = db.getDishProductJoinDao();
    }
    public LiveData<List<Product>> getallProductsFromDish(final int dishId)
    {
        return dishProductJoinDao.getProductsForDishes(dishId);
    }

    public void insert(final DishProductJoin dishProductJoin) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dishProductJoinDao.insert(dishProductJoin);
            }
        }).start();
    }

    public void delete(final DishProductJoin dishProductJoin) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dishProductJoinDao.delete(dishProductJoin);
            }
        }).start();
    }

    public LiveData<List<Product>> getListLiveData() {
        return listLiveData;
    }
}