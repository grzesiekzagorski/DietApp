package com.example.grzesiek87.dietapp.plan;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Główna baza danych aplikacji
 */
@Database(version = 1,entities = {Product.class,Dish.class,DishProductJoin.class})
public abstract class DietAppDatabase extends RoomDatabase {

    private static DietAppDatabase INSTANCE;
    public static DietAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DietAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DietAppDatabase.class, "diet_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract ProductDao getproductDao();
    public abstract DishDao getdishDao();
    public abstract  DishProductJoinDao getDishProductJoinDao();
}
