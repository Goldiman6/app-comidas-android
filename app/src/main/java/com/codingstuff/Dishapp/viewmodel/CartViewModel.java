package com.codingstuff.Dishapp.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codingstuff.Dishapp.repository.CartRepo;
import com.codingstuff.Dishapp.utils.model.DishCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<DishCart>> getAllCartItems() {
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(DishCart dishCart) {
        cartRepo.insertCartItem(dishCart);
    }

    public void updateQuantity(int id, int quantity) {
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price) {
        cartRepo.updatePrice(id, price);
    }

    public void deleteCartItem(DishCart dishCart) {
        cartRepo.deleteCartItem(dishCart);
    }

    public void deleteAllCartItems() {
        cartRepo.deleteAllCartItems();
    }
}
