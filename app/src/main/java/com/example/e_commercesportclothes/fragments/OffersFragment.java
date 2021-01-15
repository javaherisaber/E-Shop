package com.example.e_commercesportclothes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.adapters.OfferAdapter;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAddProduct;
import com.example.e_commercesportclothes.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OffersFragment extends Fragment {
    RecyclerView recyclerViewOffer;
    TextView textViewCounter;
    SportShopDatabase sportShopDatabase;
    Executor executor;
    OnAddProduct onAddProduct;
    OfferAdapter offerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_offers,container,false);
        //reference
        recyclerViewOffer=view.findViewById(R.id.recyclerViewOffer);
        executor = Executors.newSingleThreadExecutor();
        sportShopDatabase = SportShopDatabase.getInstance(getContext());

        textViewCounter = view.findViewById(R.id.textViewCounter);



        // Call "Add to basket" ,Show Text "Product successfully added to Basket"
        OnAddProduct onAddProduct = new OnAddProduct() {
            @Override
            public void onProductAdded() {

                Toast.makeText(getContext(), "Product successfully added to Basket", Toast.LENGTH_SHORT).show();
            }
        };

        //SetAdapter of BestPriceAdapter
        offerAdapter = new OfferAdapter((Context) getActivity(), getDammyDataOffers(), onAddProduct);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerViewOffer.setAdapter(offerAdapter);
        recyclerViewOffer.setLayoutManager(gridLayoutManager);





        return view;
    }





    @Override
    public void onResume() {
        super.onResume();
    }

    // Our Products of Offers on OffersFragment
    public List<Product> getDammyDataOffers() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        product1.setImageUrl("https://img01.ztat.net/article/spp-media-p1/beb93adf03923fdcaac34fba7fd6accd/55ed895c5a7342af8b6fa5f91b3f8d63.jpg?imwidth=1800");
        product2.setImageUrl("https://img01.ztat.net/article/spp-media-p1/19f46bcef6da339e8188cb0fb2be56fd/58ad78e831fd447b8e1fe470746b9221.jpg?imwidth=1800");
        product3.setImageUrl("https://mosaic03.ztat.net/vgs/media/outfit-image-mhq/ff3063249376325aa6257c1df617be56/e0eede4a3b214d368496cd878994d6c5.jpg?imwidth=1800");
        product4.setImageUrl("https://img01.ztat.net/article/spp-media-p1/30239c1f6fd73cbcab6c23bec377cc76/20070a8867a84237a4012cf9382723ef.jpg?imwidth=1800&filter=packshot");
        product5.setImageUrl("https://img01.ztat.net/article/spp-media-p1/5ac456e7bcdd36b89c1a8267e650ae8e/78d19ea107b64afdb9dec96c58c67511.jpg?imwidth=1800");
        product6.setImageUrl("https://mosaic04.ztat.net/vgs/media/outfit-image-mhq/f1e9adb1304d3e848d73bd1d6c014f50/c77ccaafe3fc44beb34480b1339e17b4.jpg?imwidth=1000");
        product1.setName("INDY PRO \nMIRAGE BRA ");
        product2.setName("Nike CROP\n - Tights");
        product3.setName("Get the Look\nvon Zalando");
        product4.setName("LEGEND ESSENTIAL2\n - Trainings");
        product5.setName("Tights");
        product6.setName("COAT-Wollmantel\nklassischer");
        product1.setPrice(49);
        product2.setPrice(49);
        product3.setPrice(49);
        product4.setPrice(49);
        product5.setPrice(49);
        product6.setPrice(49);
        product1.setRawPrice(60);
        product2.setRawPrice(60);
        product3.setRawPrice(60);
        product4.setRawPrice(60);
        product5.setRawPrice(60);
        product6.setRawPrice(60);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        return productList;
    }
}
