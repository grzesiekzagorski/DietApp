package com.example.grzesiek87.dietapp.plan;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface DishProductJoinDao {


    @Insert
    void insert(DishProductJoin dishProductJoin);

    /**
     * Zapytanie pozwalające nam wybrać wszystkie produkty z bazy danych pod wskazanym dishId
     * @param dishId id potrawy
     * @return potrawy pod wskazanym dishId
     */
    @Query("SELECT * FROM Product INNER JOIN product_dish_join ON " +
            "Product.id=product_dish_join.productId " +
            "WHERE product_dish_join.DishId=:dishId")
    LiveData<List<Product>> getProductsForDishes(final int dishId);

    @Delete
    void delete(DishProductJoin dishProductJoin);
}
