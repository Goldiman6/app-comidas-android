package com.codingstuff.Dishapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.codingstuff.Dishapp.utils.model.DishCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCartItem(DishCart dishCart);

    @Query("SELECT * FROM dish_table")
    LiveData<List<DishCart>> getAllCartItems();

    @Delete
    void deleteCartItem(DishCart dishCart);

    @Query("UPDATE dish_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id , int quantity);

    @Query("UPDATE dish_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id , double totalItemPrice);

    @Query("DELETE FROM dish_table")
    void deleteAllItems();

}
