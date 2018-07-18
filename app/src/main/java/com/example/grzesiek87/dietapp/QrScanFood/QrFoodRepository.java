package com.example.grzesiek87.dietapp.QrScanFood;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.grzesiek87.dietapp.plan.Product;

import java.util.List;


public class QrFoodRepository {
    /**
     * Deklaracja DAO
     */

    private QrFoodDao qrFoodDao;
    private LiveData<List<QrFood>> allQrFoods;

    QrFoodRepository(@NonNull Application application) {

        /**
         * Deklaracja i inicjalizacja bazy danych
         */


        QrDataBase db = QrDataBase.getDatabase(application);
        qrFoodDao = db.qrFoodDao();
        allQrFoods = qrFoodDao.getAllQrFoods();



    }

    /**
     * Implementacja metody getAllQrFoods w interfejsie QrFoodDao
     * @return zwracana jest lista produktów
     */
    public LiveData<List<QrFood>> getAllQrFoods()
    {
        return allQrFoods;
    }

    /**
     * dodanie wpisu do bazy danych
     * @param word jest to obiekt typu QrFood, który jest rekordem w bazie danych.
     */
    public void insert(final QrFood word){
        new Thread(new Runnable() {
            @Override
            public void run() {
                qrFoodDao.insert(word);
            }
        }).start();
    }

    /**
     * usuniecie wpisu z bazy danych.
     * @param word
     */
    public void delete(final QrFood word){
        new Thread(new Runnable() {
            @Override
            public void run() {
                qrFoodDao.delete(word);
            }
        }).start();
    }

}




