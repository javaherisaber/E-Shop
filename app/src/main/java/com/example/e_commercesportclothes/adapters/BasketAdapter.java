package com.example.e_commercesportclothes.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAdapterUpdate;
import com.example.e_commercesportclothes.model.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {
    Context context;
    List<Product> productList;
    SportShopDatabase sportShopDatabase;
    Executor executor = Executors.newSingleThreadExecutor();
    OnAdapterUpdate onAdapterUpdate;

    public BasketAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.sportShopDatabase = SportShopDatabase.getInstance(context);
    }

    public void setOnAdapterUpdate(OnAdapterUpdate onAdapterUpdate) {
        this.onAdapterUpdate = onAdapterUpdate;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_product_baskett, parent,false);
        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(context).load(product.getImageUrl()).into(holder.imageViewProductBasket);
        holder.textViewName.setText(product.getName());
        holder.textViewPrice.setText(product.getPrice() + "");
        holder.textViewRawPrice.setText(product.getRawPrice() + "");
        holder.textViewQuantity.setText(product.getQuantity() + "");
        holder.textViewRawPrice.setPaintFlags(holder.textViewRawPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewProductBasket;
        ImageView buttonProductAdd;
        ImageView buttonProductMines;
        ImageView imageViewProductDelete;
        TextView textViewName;
        TextView textViewPrice;
        TextView textViewQuantity;
        TextView textViewRawPrice;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewProductName);
            textViewPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewRawPrice = itemView.findViewById(R.id.textViewProductRawPrice);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            imageViewProductBasket = itemView.findViewById(R.id.imageViewProductBasket);
            buttonProductAdd = itemView.findViewById(R.id.imageViewAdd);
            buttonProductMines = itemView.findViewById(R.id.imageViewMines);
            imageViewProductDelete = itemView.findViewById(R.id.imageViewDeletee);

            //decrease amount of a Product on Layout of BasketActivity
            buttonProductMines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decProductQuantity(productList.get(getAdapterPosition()));
                }
            });

            //increase amount of a Product on Layout of BasketActivity
            buttonProductAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incProductQuantity(productList.get(getAdapterPosition()));
                }
            });

            //Complete  delete a Product on Layout of BasketActivity
            imageViewProductDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteBasketProduct(productList.get(getAdapterPosition()));

                }
            });


        }

        @Override
        public void onClick(View v) {

        }
    }


    // Process of Delete a Product (Method deleteBasketProduct)
    public void deleteBasketProduct(Product product) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sportShopDatabase.productDao().deleteProduct(product);
            }
        });
        productList.remove(product);
        onAdapterUpdate.onAdapterUpdate();


    }

    // Process of Decrease a Product (Method deleteBasketProduct)
    public void decProductQuantity(Product product) {
        if (product.getQuantity() > 1) {
            product.setQuantity(product.getQuantity() - 1);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sportShopDatabase.productDao().addProduct(product);
                }
            });
            onAdapterUpdate.onAdapterUpdate();

        }
    }


    // Process of Increase a Product (Method deleteBasketProduct)
    public void incProductQuantity(Product product) {
        product.setQuantity(product.getQuantity() + 1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                sportShopDatabase.productDao().addProduct(product);
                notifyDataSetChanged();
            }
        });
        onAdapterUpdate.onAdapterUpdate();
    }

}
