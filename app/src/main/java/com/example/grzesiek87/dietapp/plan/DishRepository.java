package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Repozytorium Dish
 */
public class DishRepository {
    private DishDao dishDao;
    private LiveData<List<Dish>> listLiveData;

    DishRepository(@NonNull Application application) {
        DietAppDatabase db = DietAppDatabase.getDatabase(application);
        dishDao = db.getdishDao();
        listLiveData = dishDao.getAllDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return listLiveData;
    }

    public void insertDish(final Dish dish){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dishDao.insertDish(dish);
            }
        }).start();
    }


}
