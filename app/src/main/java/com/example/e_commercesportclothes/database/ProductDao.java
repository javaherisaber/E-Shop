package com.example.e_commercesportclothes.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.e_commercesportclothes.model.Product;

import java.util.List;


@Dao
public interface ProductDao {

    @Query("select * from product")
    List<Product> getAllProducts();
    @Query("select * from product")
        List<Product> getAllFavorites();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(Product product);
    @Delete
    void deleteProduct(Product product);
    @Update
    void updateProduct(Product product);
    @Query("select sum(quantity) from product")
    int getSumQuantity();
}
