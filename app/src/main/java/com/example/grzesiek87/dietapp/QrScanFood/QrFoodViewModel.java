package com.example.grzesiek87.dietapp.QrScanFood;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Klasa ta umożliwia przechowywanie i zarządzanie danymi wprowadzonymi w interfejsie użytkownika
 * W naszym przypadku zawiera wywołanie metoda insert,delete oraz getAllQrFoods zdefiniowanych w
 * klasie QrFoodRepository
 */
public class QrFoodViewModel extends AndroidViewModel {
    private QrFoodRepository mRepository;
    private LiveData<List<QrFood>> allQrFoods;

    public QrFoodViewModel(@NonNull Application application) {
        super(application);

        mRepository = new QrFoodRepository(application);
        allQrFoods = mRepository.getAllQrFoods();
    }

    public void insert(final QrFood word)
    {
        mRepository.insert(word);
    }
    public void delete(final QrFood word)
    {
        mRepository.delete(word);
    }
    public LiveData<List<QrFood>> getAllQrFoods()
    {
        return allQrFoods;
    }
}

