package com.codingstuff.Dishapp.utils.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish_table")
public class DishCart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String dishName, dishBrandName;
    private int dishImage;
    private double dishPrice;

    private int quantity;
    private double totalItemPrice;


    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishBrandName() {
        return dishBrandName;
    }

    public void setDishBrandName(String dishBrandName) {
        this.dishBrandName = dishBrandName;
    }

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
