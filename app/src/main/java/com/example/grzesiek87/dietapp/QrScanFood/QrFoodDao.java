package com.example.grzesiek87.dietapp.QrScanFood;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Interfejs zawierający metody dodania, usunięcia oraz wyświetlenia wszystkich produktów
 * znajdujących się w bazie danych.
 */
@Dao
public interface QrFoodDao {

        @Insert
        void insert(QrFood word);

        @Delete
        void delete(QrFood word);

        @Query("Select * from QrFood")
        LiveData<List<QrFood>> getAllQrFoods();
}

