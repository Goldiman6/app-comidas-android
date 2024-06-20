package com.codingstuff.Dishapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DishItem implements Parcelable {

    private String dishName, dishBrandName, dishDetails;
    private int dishImage;
    private double dishPrice;

    public DishItem(String dishName, String dishBrandName, String dishDetails, int dishImage, double dishPrice) {
        this.dishName = dishName;
        this.dishBrandName = dishBrandName;
        this.dishImage = dishImage;
        this.dishPrice = dishPrice;
        this.dishDetails = dishDetails;
    }

    protected DishItem(Parcel in) {
        dishName = in.readString();
        dishBrandName = in.readString();
        dishDetails = in.readString();
        dishImage = in.readInt();
        dishPrice = in.readDouble();
    }

    public static final Creator<DishItem> CREATOR = new Creator<DishItem>() {
        @Override
        public DishItem createFromParcel(Parcel in) {
            return new DishItem(in);
        }

        @Override
        public DishItem[] newArray(int size) {
            return new DishItem[size];
        }
    };

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

    public String getDishDetails() {
        return dishDetails;
    }

    public void setDishDetails(String dishDetails) {
        this.dishDetails = dishDetails;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dishName);
        parcel.writeString(dishBrandName);
        parcel.writeString(dishDetails);
        parcel.writeInt(dishImage);
        parcel.writeDouble(dishPrice);
    }
}
