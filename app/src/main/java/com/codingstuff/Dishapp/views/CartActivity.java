package com.codingstuff.Dishapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codingstuff.Dishapp.R;
import com.codingstuff.Dishapp.utils.adapter.CartAdapter;
import com.codingstuff.Dishapp.utils.model.DishCart;
import com.codingstuff.Dishapp.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<DishCart>>() {
            @Override
            public void onChanged(List<DishCart> dishCarts) {
                double price = 0;
                cartAdapter.setDishCartList(dishCarts);
                for (int i = 0; i< dishCarts.size(); i++){
                    price = price + dishCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onDeleteClicked(DishCart dishCart) {
        cartViewModel.deleteCartItem(dishCart);
    }

    @Override
    public void onPlusClicked(DishCart dishCart) {
        int quantity = dishCart.getQuantity() + 1;
        cartViewModel.updateQuantity(dishCart.getId() , quantity);
        cartViewModel.updatePrice(dishCart.getId() , quantity* dishCart.getDishPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(DishCart dishCart) {
        int quantity = dishCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(dishCart.getId() , quantity);
            cartViewModel.updatePrice(dishCart.getId() , quantity* dishCart.getDishPrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(dishCart);
        }

    }
}