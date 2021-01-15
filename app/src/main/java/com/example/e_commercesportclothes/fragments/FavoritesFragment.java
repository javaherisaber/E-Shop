package com.example.e_commercesportclothes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.adapters.FavoriteAdapter;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAdapterFavorite;
import com.example.e_commercesportclothes.model.Favorite;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // reference
        RecyclerView recyclerViewFavorite = view.findViewById(R.id.recyclerViewFavorite);
        SportShopDatabase sportShopDatabase=SportShopDatabase.getInstance(getContext());
        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Favorite> favoriteList1=sportShopDatabase.favoriteDao().getAllFavorites();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FavoriteAdapter favoriteAdapter= new FavoriteAdapter(getContext(), favoriteList1);
                        favoriteAdapter.setOnAdapterFavorite(new OnAdapterFavorite() {
                            @Override
                            public void onAdapterFavorite() {
                                favoriteAdapter.notifyDataSetChanged();

                            }
                        });
                        recyclerViewFavorite.setAdapter(favoriteAdapter);
                        recyclerViewFavorite.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
                    }
                });

            }
        });

        return view;

    }
}
