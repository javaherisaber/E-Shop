package com.example.e_commercesportclothes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.adapters.setsAdapter;
import com.example.e_commercesportclothes.listener.OnAddProduct;
import com.example.e_commercesportclothes.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SneakerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sneakers, container, false);
        // reference
        RecyclerView recyclerViewSneakers = view.findViewById(R.id.recyclerViewSneakers);


        // Call "Add to basket" ,Show Text "Product successfully added to Basket"
        OnAddProduct onAddProduct = new OnAddProduct() {
            @Override
            public void onProductAdded() {
                Toast.makeText(getContext(), "Product successfully added to Basket", Toast.LENGTH_SHORT).show();
            }
        };

        // LayoutManager  + Adapter
        setsAdapter setsAdapter = new setsAdapter((Context) getActivity(), getDammyData2(), onAddProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewSneakers.setAdapter(setsAdapter);
        recyclerViewSneakers.setLayoutManager(gridLayoutManager);

        return view;
    }

    // Our Products on SetsFragment
    public List<Product> getDammyData2() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        product1.setImageUrl("https://img01.ztat.net/article/spp-media-p1/a9ebcd8bb58b387cad8be16c18019b0a/ff23e832d60d4605870159987c8dc19d.jpg?imwidth=1800&filter=packshot");
        product2.setImageUrl("https://img01.ztat.net/article/spp-media-p1/71cd66150775350683d69c8c29e1b49a/10089580f3dd4bd9abe515962f9300eb.jpg?imwidth=1800&filter=packshot");
        product3.setImageUrl("https://img01.ztat.net/article/spp-media-p1/cf46acb37f483927b93589ca9d464970/ddd6ce40b5104b5d80271627be623acb.jpg?imwidth=1800&filter=packshot");
        product4.setImageUrl("https://dkstatics-public.digikala.com/digikala-products/44403df4eff55aa4a2d74c594c9a1c1081ca2e49_1601982242.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product5.setImageUrl("https://dkstatics-public.digikala.com/digikala-products/f9f5fb42da67be38974ada01157fd36b9b402128_1595353715.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product6.setImageUrl("https://dkstatics-public.digikala.com/digikala-products/115342056.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product1.setName("REVOLUTION 5 \n Laufschuh Neutral");
        product2.setName( "COURT AIR \nMAX WILDCARD");
        product3.setName(" Reebok\n" +
                "RIDGERIDER 6.0 ");
        product4.setName(" استیشن کد SAAT-1005-39");
        product5.setName(" استیشن کد SAAT-1005-39");
        product6.setName(" استیشن کد SAAT-1005-39");
        product1.setPrice(49);
        product2.setPrice(489);
        product3.setPrice(49);
        product4.setPrice(29);
        product5.setPrice(49);
        product6.setPrice(469);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        return productList;
    }

}
