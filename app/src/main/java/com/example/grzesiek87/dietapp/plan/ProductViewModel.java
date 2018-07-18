package com.example.grzesiek87.dietapp.plan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import java.util.List;

/**
 * ViewModel produktu
 */
public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ProductRepository(application);
        allProducts = mRepository.getAllProducts();
    }

    public void insertProduct(final Product product)
    {
        mRepository.insertProduct(product);
    }
    public LiveData<List<Product>> getAllProducts() { return allProducts; }
}
