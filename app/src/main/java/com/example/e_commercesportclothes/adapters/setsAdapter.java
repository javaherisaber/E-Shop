package com.example.e_commercesportclothes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAdapterFavorite;
import com.example.e_commercesportclothes.listener.OnAdapterUpdate;
import com.example.e_commercesportclothes.listener.OnAddProduct;
import com.example.e_commercesportclothes.model.Favorite;
import com.example.e_commercesportclothes.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class setsAdapter extends RecyclerView.Adapter<setsAdapter.ViewHolderTrainingsset> {
    List<Product> productList;
    Context context;
    OnAddProduct onAddProduct;
    Product product;
     SportShopDatabase sportShopDatabase;
     OnAdapterUpdate onAdapterUpdate;



    public setsAdapter(Context context, List<Product> productList, OnAddProduct onAddProduct) {
        this.context = context;
        this.productList = productList;
        this.onAddProduct = onAddProduct;
        sportShopDatabase = SportShopDatabase.getInstance(context);

    }


    //ViewHolder for show our Products on Layout of Set and and Sneakers
    @NonNull
    @Override
    public ViewHolderTrainingsset onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);
        return new ViewHolderTrainingsset(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrainingsset holder, int position) {
        product=  productList.get(position);
        Glide.with(context).load(product.getImageUrl()).into(holder.imageViewProduct);
        holder.textViewName.setText(product.getName());
        holder.textViewPrice.setText(product.getPrice() + "");


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolderTrainingsset extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewProduct;
        TextView textViewName;
        TextView textViewPrice;
        Button buttonAddToBasket;
        ToggleButton toggleButton;


        public ViewHolderTrainingsset(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewName = itemView.findViewById(R.id.textViewProductName);
            textViewPrice = itemView.findViewById(R.id.textViewProductPrice);
            buttonAddToBasket = itemView.findViewById(R.id.buttonAddToBasket);
            toggleButton = itemView.findViewById(R.id.toggleButton);
            buttonAddToBasket.setOnClickListener(this);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Executor executor = Executors.newSingleThreadExecutor();
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                addOrRemoveFavorite(product,true);
                            }
                        });
                    } else {
                        addOrRemoveFavorite(product,false);

                    }
                }
            });


        }

        // Process of Add our Product To Basket on Layout of Sets and Sneakers
        @Override
        public void onClick(View v) {
            final Product product = productList.get(getAdapterPosition());
            product.setQuantity(product.getQuantity() + 1);
            final SportShopDatabase sportShopDatabase = SportShopDatabase.getInstance(context);
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sportShopDatabase.productDao().addProduct(product);
                }
            });
            onAddProduct.onProductAdded();

        }


    }
    private List<Favorite> addOrRemoveFavorite(Product product,boolean isAdd){
        List<Favorite> favoriteList=new ArrayList<>();
        Favorite favorite=new Favorite();
        favorite.setId(product.getId());
        favorite.setName(product.getName());
        favorite.setImageUrl(product.getImageUrl());
        favorite.setPrice(product.getPrice());
        favorite.setRawPrice(product.getPrice());
        if (isAdd){
            sportShopDatabase=SportShopDatabase.getInstance(context);
            sportShopDatabase.favoriteDao().addFavorite(favorite);
            return favoriteList;
        }else {
            sportShopDatabase=SportShopDatabase.getInstance(context);
            sportShopDatabase.favoriteDao().deleteFavorite(favorite);
            return favoriteList;
        }
    }


}

