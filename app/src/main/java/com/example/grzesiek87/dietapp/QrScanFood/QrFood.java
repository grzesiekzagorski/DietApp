
/*
Klasa przedstawiająca wpis znajdujący się w bazie danych.
 */

package com.example.grzesiek87.dietapp.QrScanFood;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Kolumny rekordu.
 */
@Entity
public class QrFood {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String weight;
    public String energy;
}

