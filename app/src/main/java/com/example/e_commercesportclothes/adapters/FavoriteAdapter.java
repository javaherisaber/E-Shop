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
import com.example.e_commercesportclothes.listener.OnAdapterFavorite;
import com.example.e_commercesportclothes.model.Favorite;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolderFavorite> {
    List<Favorite> favoriteList;
    Context context;
    OnAdapterFavorite onAdapterFavorite;
    SportShopDatabase sportShopDatabase;

    public FavoriteAdapter(Context applicationContext, List<Favorite> favoriteList) {
        this.context = applicationContext;
        this.favoriteList = favoriteList;
        sportShopDatabase = SportShopDatabase.getInstance(context);

    }


    public void setOnAdapterFavorite(OnAdapterFavorite onAdapterFavorite) {
        this.onAdapterFavorite = onAdapterFavorite;
    }


    //ViewHolder for show our Products on Layout of Favorites
    @NonNull
    @Override
    public ViewHolderFavorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false);
        return new ViewHolderFavorite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavorite holder, int position) {
        Favorite favorite = favoriteList.get(position);
        Glide.with(context).load(favorite.getImageUrl()).into(holder.imageViewProduct);
        holder.textViewName.setText(favorite.getName());
        holder.textViewPrice.setText(favorite.getPrice() + "");
        holder.textViewrawPrice.setText(favorite.getRawPrice() + "");
        holder.textViewrawPrice.setPaintFlags(holder.textViewrawPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class ViewHolderFavorite extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewProduct;
        ImageView imageViewDeleteFav;
        TextView textViewName;
        TextView textViewPrice;
        TextView textViewrawPrice;

        public ViewHolderFavorite(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewName = itemView.findViewById(R.id.textViewProductName);
            textViewPrice = itemView.findViewById(R.id.textViewProductPrice);
            imageViewDeleteFav = itemView.findViewById(R.id.imageViewDeleteFav);
            textViewrawPrice = itemView.findViewById(R.id.textViewRawPrice);
            imageViewDeleteFav.setOnClickListener(this);

        }

        // Process of Add our Product To Basket on Layout of Favorites
        @Override
        public void onClick(View v) {
            deleteFavorite(favoriteList.get(getAdapterPosition()));
        }

        private void deleteFavorite(Favorite favorite) {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sportShopDatabase.favoriteDao().deleteFavorite(favorite);

                }
            });
            favoriteList.remove(favorite);
            onAdapterFavorite.onAdapterFavorite();
        }


    }
}

