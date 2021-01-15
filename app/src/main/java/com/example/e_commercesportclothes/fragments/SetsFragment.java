package com.example.e_commercesportclothes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

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

public class SetsFragment extends Fragment {
    List<Product> productList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);

        // reference
        RecyclerView recyclerViewSet = view.findViewById(R.id.recyclerViewTrainingSet);
        ToggleButton toggleButton=view.findViewById(R.id.toggleButton);

        //

        // Call "Add to basket" ,Show Text "Product successfully added to Basket"
        OnAddProduct onAddProduct = new OnAddProduct() {
            @Override
            public void onProductAdded() {
                Toast.makeText(getContext(), "Product successfully added to Basket", Toast.LENGTH_SHORT).show();
            }
        };


        // LayoutManager  + Adapter
        setsAdapter setsAdapter = new setsAdapter((Context) getActivity(), getDammyData(), onAddProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewSet.setAdapter(setsAdapter);
        recyclerViewSet.setLayoutManager(gridLayoutManager);

        return view;
    }


    // Our Products on SetsFragment
    public List<Product> getDammyData() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        product1.setImageUrl("https://ae01.alicdn.com/kf/HTB1BmyvhA9WBuNjSspeq6yz5VXal/Sport-Clothing-Set-Men-Running-Jogging-Suits-Male-Gym-Fitness-Body-building-Sportwear-Men-s-Hoodies.jpg_q50.jpg");
        product2.setImageUrl("https://images.asos-media.com/products/asos-design-set-oversized-hoodie-in-gray-acid-wash-with-chest-print/21568643-1-washedblack?$n_320w$&wid=317&fit=constrain");
        product3.setImageUrl("https://images.asos-media.com/products/asos-dark-future-co-ord-oversized-half-zip-sweatshirt-in-gray-marl-with-front-logo/21787065-1-greymarl?$n_320w$&wid=317&fit=constrain");
        product4.setImageUrl("https://www.sakilatuxd.com/send_imgs.php?img=abe91d36bea45a343609fca197da54079HR0cDovL2be91d36bea45a343609fca197da54079kuZWJheWltZy5jb20vaW1hZ2VzL2cvVVZBQUFPU3dQUUJjNjY2WC9zLWw1MDAuanBn");
        product5.setImageUrl("https://www.sakilatuxd.com/send_imgs.php?img=abe91d36bea45a343609fca197da54079HR0cDovL2be91d36bea45a343609fca197da54079kuZWJheWltZy5jb20vaW1hZ2VzL2cvYlNjQUFPU3c4a1JjUXdiNS9zLWw1MDAuanBn");
        product6.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyVsyWF_9jqNyEPN7tarihwf9giH6ZDXXCag&usqp=CAU");
        product1.setName(" Set Men Running Jogging Suits");
        product2.setName(" ASOS DESIGN tracksuit set");
        product3.setName("ASOS Dark Future set");
        product4.setName("Nicolas Cage 3D Print T ");
        product5.setName("16sixty Men’s Grey \n Plain Jogging Suit");
        product6.setName(" Baby Boys Girls Tik tok \n Sport Clothing Set Boy");
        product1.setPrice(29.99);
        product2.setPrice(49);
        product3.setPrice(53.99);
        product4.setPrice(19.99);
        product5.setPrice(49);
        product6.setPrice(49);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        return productList;
    }

}
