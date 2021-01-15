package com.example.e_commercesportclothes.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.e_commercesportclothes.model.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("select * from favorite")
    List<Favorite> getAllFavorites();


    @Query("select exists (select 1 from Favorite where id=:itemId )")
    int isFavorite(int itemId);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Insert
    void addFavorite(Favorite favorite);
}
