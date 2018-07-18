package com.example.grzesiek87.dietapp.plan;

        import android.arch.lifecycle.LiveData;
        import android.arch.persistence.room.Dao;
        import android.arch.persistence.room.Insert;
        import android.arch.persistence.room.Query;

        import java.util.List;

/**
 * Dao potrawy
 */
@Dao
public interface DishDao {

    @Insert
    public void insertDish(Dish dish);

    @Query("SELECT * FROM Dish")
    public LiveData<List<Dish>> getAllDishes();
}
