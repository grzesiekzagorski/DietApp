package com.example.grzesiek87.dietapp.plan;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Dao Produktu
 */
@Dao
public interface ProductDao {

    @Insert
    public void insertProduct(Product product);

    @Update
    public void updateProduct(Product product);

    @Delete
    public void deleteProduct(Product product);

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllProducts();

}

