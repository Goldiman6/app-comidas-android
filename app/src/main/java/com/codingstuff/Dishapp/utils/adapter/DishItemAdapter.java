package com.codingstuff.Dishapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.Dishapp.R;
import com.codingstuff.Dishapp.utils.model.DishItem;

import java.util.List;

public class DishItemAdapter extends RecyclerView.Adapter<DishItemAdapter.DishItemViewHolder> {

    private List<DishItem> dishItemList;
    private DishClickedListeners dishClickedListeners;
    public DishItemAdapter(DishClickedListeners dishClickedListeners){
        this.dishClickedListeners = dishClickedListeners;
    }
    public void setDishItemList(List<DishItem> dishItemList){
        this.dishItemList = dishItemList;
    }
    @NonNull
    @Override
    public DishItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_dish, parent , false);
        return new DishItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishItemViewHolder holder, int position) {
        DishItem dishItem = dishItemList.get(position);
        holder.dishNameTv.setText(dishItem.getDishName());
        holder.dishBrandNameTv.setText(dishItem.getDishBrandName());
        holder.dishPriceTv.setText(String.valueOf(dishItem.getDishPrice()));
        holder.dishImageView.setImageResource(dishItem.getDishImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishClickedListeners.onCardClicked(dishItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishClickedListeners.onAddToCartBtnClicked(dishItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dishItemList == null){
            return 0;
        }else{
            return dishItemList.size();
        }
    }

    public class DishItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView dishImageView , addToCartBtn;
        private TextView dishNameTv, dishBrandNameTv, dishPriceTv;
        private CardView cardView;
        public DishItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachDishCardView);
            addToCartBtn = itemView.findViewById(R.id.eachDishAddToCartBtn);
            dishNameTv = itemView.findViewById(R.id.eachDishName);
            dishImageView = itemView.findViewById(R.id.eachDishIv);
            dishBrandNameTv = itemView.findViewById(R.id.eachDishBrandNameTv);
            dishPriceTv = itemView.findViewById(R.id.eachDishPriceTv);
        }
    }

    public interface DishClickedListeners {
        void onCardClicked(DishItem shoe);
        void onAddToCartBtnClicked(DishItem dishItem);
    }
}
