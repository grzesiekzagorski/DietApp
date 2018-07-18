package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Viewmodel DishProductJoinView
 */
public class DishProductJoinViewModel extends AndroidViewModel {
    private DishProductJoinRepository dishProductJoinRepository;
    private LiveData<List<Product>> allProductsFromDish;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public DishProductJoinViewModel(@NonNull Application application) {
        super(application);
        dishProductJoinRepository = new DishProductJoinRepository(application);
        allProductsFromDish = dishProductJoinRepository.getallProductsFromDish(1);
    }
    public void insert(final DishProductJoin dishProductJoin){
        dishProductJoinRepository.insert(dishProductJoin);
    }
    public void delete(final DishProductJoin dishProductJoin){
        dishProductJoinRepository.delete(dishProductJoin);
    }
    public LiveData<List<Product>> getAllProductsFromDish(int index){
        return dishProductJoinRepository.getallProductsFromDish(index);
    }
}
