package com.codingstuff.Dishapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codingstuff.Dishapp.dao.CartDAO;
import com.codingstuff.Dishapp.utils.model.DishCart;

@Database(entities = {DishCart.class} , version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static synchronized  CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class , "DishDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
