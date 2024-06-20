package com.codingstuff.Dishapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.codingstuff.Dishapp.R;
import com.codingstuff.Dishapp.utils.model.DishCart;
import com.codingstuff.Dishapp.utils.model.DishItem;
import com.codingstuff.Dishapp.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView dishImageView;
    private TextView dishNameTV, dishBrandNameTV, dishPriceTV, dishDetailsTV;
    private AppCompatButton addToCartBtn;
    private DishItem dish;
    private CartViewModel viewModel;
    private List<DishCart> dishCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        dish = getIntent().getParcelableExtra("dishItem");
        initializeVariables();

        viewModel.getAllCartItems().observe(this, new Observer<List<DishCart>>() {
            @Override
            public void onChanged(List<DishCart> dishCarts) {
                dishCartList.addAll(dishCarts);
            }
        });

        if (dish != null) {
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom();
            }
        });

    }

    private void insertToRoom(){
        DishCart dishCart = new DishCart();
        dishCart.setDishName(dish.getDishName());
        dishCart.setDishBrandName(dish.getDishBrandName());
        dishCart.setDishPrice(dish.getDishPrice());
        dishCart.setDishImage(dish.getDishImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!dishCartList.isEmpty()){
            for(int i = 0; i< dishCartList.size(); i++){
                if (dishCart.getDishName().equals(dishCartList.get(i).getDishName())){
                    quantity[0] = dishCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = dishCartList.get(i).getId();
                }
            }
        }

        if (quantity[0]==1){
            dishCart.setQuantity(quantity[0]);
            dishCart.setTotalItemPrice(quantity[0]* dishCart.getDishPrice());
            viewModel.insertCartItem(dishCart);
        }else{

            viewModel.updateQuantity(id[0] ,quantity[0]);
            viewModel.updatePrice(id[0] , quantity[0]* dishCart.getDishPrice());
        }

        startActivity(new Intent(DetailedActivity.this , CartActivity.class));
    }

    private void setDataToWidgets() {
        dishNameTV.setText(dish.getDishName());
        dishBrandNameTV.setText(dish.getDishBrandName());
        dishDetailsTV.setText(dish.getDishDetails());
        dishPriceTV.setText(String.valueOf(dish.getDishPrice()));
        dishImageView.setImageResource(dish.getDishImage());
    }

    private void initializeVariables() {

        dishCartList = new ArrayList<>();
        dishImageView = findViewById(R.id.detailActivityDishIV);
        dishNameTV = findViewById(R.id.detailActivityDishNameTv);
        dishBrandNameTV = findViewById(R.id.detailActivityDishBrandNameTv);
        dishDetailsTV = findViewById(R.id.detailsDish);
        dishPriceTV = findViewById(R.id.detailActivityDishPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }
}