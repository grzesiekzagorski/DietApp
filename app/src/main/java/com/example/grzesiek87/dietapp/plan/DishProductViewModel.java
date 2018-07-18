package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Viewmodel DishProduct
 */
public class DishProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    private DishRepository dishRepository;
    private DishProductJoinRepository dishProductJoinRepository;
    private LiveData<List<Product>> liveDataProducts;
    private LiveData<List<Dish>> liveDataDish;
    private LiveData<List<Product>> liveDataProductDish;
    private int index;



    public DishProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        dishRepository = new DishRepository(application);
        dishProductJoinRepository = new DishProductJoinRepository(application);
        liveDataProducts = productRepository.getAllProducts();
        liveDataDish = dishRepository.getAllDishes();
        liveDataProductDish = dishProductJoinRepository.getallProductsFromDish(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void insertProduct(final Product product)
    {
        productRepository.insertProduct(product);
    }
    public LiveData<List<Product>> getAllProducts() { return liveDataProducts; }

    public void insertDishProduct(final DishProductJoin dishProductJoin){
        dishProductJoinRepository.insert(dishProductJoin);
    }
    public LiveData<List<Product>> getAllProductsFromDish(int index){
        return dishProductJoinRepository.getallProductsFromDish(index);
    }

    public void insertDish(final Dish dish){
        dishRepository.insertDish(dish);
    }

    public LiveData<List<Dish>> getAllDishes() {
        return liveDataDish;
    }
}
