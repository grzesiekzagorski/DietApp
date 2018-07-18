package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * ViewModel klasy Dish
 */
public class DishViewModel extends AndroidViewModel{
    private DishRepository dishRepository;
    private LiveData<List<Dish>> listLiveData;

    public DishViewModel(@NonNull Application application) {
        super(application);
        dishRepository = new DishRepository(application);
        listLiveData = dishRepository.getAllDishes();
    }

    public void insertDish(final Dish dish){
        dishRepository.insertDish(dish);
    }

    public LiveData<List<Dish>> getAllDishes() {
        return listLiveData;
    }
}
