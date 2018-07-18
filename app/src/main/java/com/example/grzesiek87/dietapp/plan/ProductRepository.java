package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Repozytorium Produktu
 */
public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    ProductRepository(@NonNull Application application) {
        DietAppDatabase db = DietAppDatabase.getDatabase(application);
        productDao = db.getproductDao();
        allProducts = productDao.getAllProducts();
    }


    public LiveData<List<Product>> getAllProducts()
    {
        return allProducts;
    }

    public void insertProduct(final Product product){
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        }).start();
    }

    public void deleteProduct(final Product product){
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDao.deleteProduct(product);
            }
        }).start();
    }
}
