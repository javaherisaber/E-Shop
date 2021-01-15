package com.example.e_commercesportclothes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.e_commercesportclothes.model.Favorite;
import com.example.e_commercesportclothes.model.Product;

@Database(entities = {Product.class, Favorite.class},exportSchema = false,version = 2)
public abstract class SportShopDatabase extends RoomDatabase {
    public static final String DB_NAME = "database";
    static SportShopDatabase instance;


    public static synchronized SportShopDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context,SportShopDatabase.class
            ,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;

    }
    public  abstract ProductDao productDao();
    public  abstract FavoriteDao favoriteDao();

}
