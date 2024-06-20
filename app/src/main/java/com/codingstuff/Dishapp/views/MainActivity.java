package com.codingstuff.Dishapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.Dishapp.R;
import com.codingstuff.Dishapp.utils.adapter.DishItemAdapter;
import com.codingstuff.Dishapp.utils.model.DishCart;
import com.codingstuff.Dishapp.utils.model.DishItem;
import com.codingstuff.Dishapp.viewmodel.CartViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DishItemAdapter.DishClickedListeners {

    private RecyclerView recyclerView;
    private List<DishItem> dishItemList;
    private DishItemAdapter adapter;
    private CartViewModel viewModel;
    private List<DishCart> dishCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setUpList();

        adapter.setDishItemList(dishItemList);
        recyclerView.setAdapter(adapter);


        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<DishCart>>() {
            @Override
            public void onChanged(List<DishCart> dishCarts) {
                dishCartList.addAll(dishCarts);
            }
        });
    }

    private void setUpList() {
        dishItemList.add(new DishItem("Bandeja Paisa", "El paisa","arroz, fríjoles, carne molida, chicharrón, chorizo, huevo, aguacate, arepa y 1 tajada de plátano maduro, todo en porciones iguales.", R.drawable.bandeja_paisa, 15));
        dishItemList.add(new DishItem("Pollo Broaster", "Kokoriko","1 pollo, papas y salsas", R.drawable.pollo_broaster, 20));
        dishItemList.add(new DishItem("Sushi", "Teriyaki", "3 uramaki rolls, 3 california rolls,3 ninja rolls",R.drawable.sushi, 18));
        dishItemList.add(new DishItem("Hamburguesa", "Burger King","pan, tomate, lechuga, tocineta, doble carne, queso americano, ketchup", R.drawable.hamburguesa, 16.5));
        dishItemList.add(new DishItem("Ceviche", "El perú", "pescado blanco, camarones, cebolla morada, pico de gallo, limón, platano verde frito, ",R.drawable.ceviche, 20));
        dishItemList.add(new DishItem("Rib Eye", "Steaks&Beer","un rib eye de 200g asado a la parilla con Knorr Professional caldo de pollo, pimienta negra molida", R.drawable.rib_eye, 22));
        dishItemList.add(new DishItem("Sudado de Pescado", "Doña Juana","", R.drawable.sudado_pescado, 12));
        dishItemList.add(new DishItem("Brownie", "Las vegas","cacao en polvo sin azucar, mantequilla sin sal, huevo, azucar,harina, esencia de vainilla, sal, nueces", R.drawable.brownie, 15));

    }

    private void initializeVariables() {

        cartImageView = findViewById(R.id.cartIv);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        dishCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        dishItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new DishItemAdapter(this);

    }

    @Override
    public void onCardClicked(DishItem dish) {

        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("dishItem", dish);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(DishItem dishItem) {
        DishCart dishCart = new DishCart();
        dishCart.setDishName(dishItem.getDishName());
        dishCart.setDishBrandName(dishItem.getDishBrandName());
        dishCart.setDishPrice(dishItem.getDishPrice());
        dishCart.setDishImage(dishItem.getDishImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!dishCartList.isEmpty()) {
            for (int i = 0; i < dishCartList.size(); i++) {
                if (dishCart.getDishName().equals(dishCartList.get(i).getDishName())) {
                    quantity[0] = dishCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = dishCartList.get(i).getId();
                }
            }
        }

        Log.d("TAG", "onAddToCartBtnClicked: " + quantity[0]);

        if (quantity[0] == 1) {
            dishCart.setQuantity(quantity[0]);
            dishCart.setTotalItemPrice(quantity[0] * dishCart.getDishPrice());
            viewModel.insertCartItem(dishCart);
        } else {
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * dishCart.getDishPrice());
        }

        makeSnackBar("Item Added To Cart");
    }

    private void makeSnackBar(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }
}