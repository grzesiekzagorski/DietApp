package com.example.grzesiek87.dietapp.plan;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Określenie relacji pomiedzy tabelami
 */
@Entity(tableName = "product_dish_join",
        primaryKeys = {"dishId","productId"},foreignKeys = {
        @ForeignKey(entity = Dish.class,
                parentColumns = "id",
                childColumns = "dishId"),
        @ForeignKey(entity = Product.class,
                parentColumns = "id",
                childColumns = "productId")
})
public class DishProductJoin {
    public final int dishId;
    public final int productId;

    public DishProductJoin(int dishId, int productId) {
        this.dishId = dishId;
        this.productId = productId;
    }
}
