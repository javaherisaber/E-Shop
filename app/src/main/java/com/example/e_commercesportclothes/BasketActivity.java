package com.example.e_commercesportclothes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercesportclothes.adapters.BasketAdapter;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAdapterUpdate;
import com.example.e_commercesportclothes.model.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        //reference
        RecyclerView recyclerViewBasket=findViewById(R.id.recyclerViewBasket);
        SportShopDatabase sportShopDatabase=SportShopDatabase.getInstance(this);
        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Product> productList=sportShopDatabase.productDao().getAllProducts();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BasketAdapter basketAdapter=new BasketAdapter(getApplicationContext(), productList);
                        basketAdapter.setOnAdapterUpdate(new OnAdapterUpdate() {
                            @Override
                            public void onAdapterUpdate() {
                                basketAdapter.notifyDataSetChanged();
                            }
                        });
                        recyclerViewBasket.setAdapter(basketAdapter);
                        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });

            }
        });





    }
}