package com.example.grzesiek87.dietapp.QrScanFood;




import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Utworzenie bazy danych
 */
@Database(entities = {QrFood.class}, version = 1)
public abstract class
QrDataBase extends RoomDatabase {
    private static QrDataBase INSTANCE;

    public static QrDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QrDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QrDataBase.class, "qr_food_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract QrFoodDao qrFoodDao();
}
