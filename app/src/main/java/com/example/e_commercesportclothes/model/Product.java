package com.example.e_commercesportclothes.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Product {
    @PrimaryKey
    @NonNull
    String id;
    String name;
    String imageUrl;
    double price;
    double rawPrice;
    int quantity;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(double rawPrice) {
        this.rawPrice = rawPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product() {
        this.id = UUID.randomUUID().toString();
    }
}
