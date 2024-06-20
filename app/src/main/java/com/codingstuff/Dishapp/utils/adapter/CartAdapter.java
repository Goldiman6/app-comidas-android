package com.codingstuff.Dishapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.Dishapp.R;
import com.codingstuff.Dishapp.utils.model.DishCart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHodler> {

    private CartClickedListeners cartClickedListeners;
    private List<DishCart> dishCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setDishCartList(List<DishCart> dishCartList) {
        this.dishCartList = dishCartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHodler holder, int position) {

        DishCart dishCart = dishCartList.get(position);
        holder.dishImageView.setImageResource(dishCart.getDishImage());
        holder.dishNameTv.setText(dishCart.getDishName());
        holder.dishBrandNameTv.setText(dishCart.getDishBrandName());
        holder.dishQuantity.setText(dishCart.getQuantity() + "");
        holder.dishPriceTv.setText(dishCart.getTotalItemPrice() + "");


        holder.deleteDishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(dishCart);
            }
        });


        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(dishCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(dishCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dishCartList == null) {
            return 0;
        } else {
            return dishCartList.size();
        }
    }

    public class CartViewHodler extends RecyclerView.ViewHolder {

        private TextView dishNameTv, dishBrandNameTv, dishPriceTv, dishQuantity;
        private ImageView deleteDishBtn;
        private ImageView dishImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHodler(@NonNull View itemView) {
            super(itemView);

            dishNameTv = itemView.findViewById(R.id.eachCartItemName);
            dishBrandNameTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            dishPriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteDishBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            dishImageView = itemView.findViewById(R.id.eachCartItemIV);
            dishQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }

    public interface CartClickedListeners {
        void onDeleteClicked(DishCart dishCart);

        void onPlusClicked(DishCart dishCart);

        void onMinusClicked(DishCart dishCart);
    }
}
