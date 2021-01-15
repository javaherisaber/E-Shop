package com.example.e_commercesportclothes.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercesportclothes.BasketActivity;
import com.example.e_commercesportclothes.R;
import com.example.e_commercesportclothes.account.ActivityLogin;
import com.example.e_commercesportclothes.adapters.BestPriceAdapter;
import com.example.e_commercesportclothes.adapters.GlideImageLoadingService;
import com.example.e_commercesportclothes.adapters.MainSliderAdapter;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.listener.OnAddProduct;
import com.example.e_commercesportclothes.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment {
    ImageView imageViewHappyNewYear;
    ImageView imageViewAccount;
    TextView textViewCounter;
    ImageView imageViewBasket;
    TextView textViewShowUsername;
    TextView textViewShowEmailUser;
    ImageView imageViewLogOut;
    SportShopDatabase sportShopDatabase;
    Executor executor;
    RecyclerView recyclerViewBestPrice;
    BestPriceAdapter bestPriceAdapter;
    FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //Slider
        Slider.init(new GlideImageLoadingService(getContext()));
        Slider slider = view.findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());


        // Reference
        imageViewLogOut = view.findViewById(R.id.imageViewLogOut);
        textViewShowUsername = view.findViewById(R.id.textViewShowUserName);
        textViewShowEmailUser = view.findViewById(R.id.textViewShowEmailUser);
        textViewCounter = view.findViewById(R.id.textViewCounter);
        recyclerViewBestPrice = view.findViewById(R.id.recyclerViewBestPrice);
        imageViewBasket = view.findViewById(R.id.imageViewBasket);
        imageViewAccount = view.findViewById(R.id.profile_image);
        executor = Executors.newSingleThreadExecutor();
        sportShopDatabase = SportShopDatabase.getInstance(getContext());
        imageViewHappyNewYear = view.findViewById(R.id.imageViewHappyNeuYear);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
               for (UserInfo profile : user.getProviderData()) {
                   // Id of the provider (ex: google.com)
                   String providerId = profile.getProviderId();
                   // UID specific to the provider
                   String uid = profile.getUid();
                   // Name, email address, and profile photo Url
                   String name = profile.getDisplayName();
                   textViewShowUsername.setText(name);
                   String email = profile.getEmail();
                   textViewShowEmailUser.setText(email);
                   imageViewLogOut.setVisibility(View.VISIBLE);
                   textViewShowEmailUser.setVisibility(View.VISIBLE);

               }
           }

        //       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //       if (user != null) {
        //           String uid = user.getUid();
        //           firebaseFirestore=FirebaseFirestore.getInstance();
        //           DocumentReference documentReference = firebaseFirestore.collection("users").document(uid);
        //           documentReference.addSnapshotListener(executor, new EventListener<DocumentSnapshot>() {
        //               @Override
        //               public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
        //                   assert value != null;
        //                   textViewShowUsername.setText(value.getString("fName"));
        //                   textViewShowEmailUser.setText(value.getString("email"));
        //               }
        //           });
        //       }

        //


        textViewShowUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivityLogin.class));
            }
        });


        // Call "Add to basket" ,Show Text "Product successfully added to Basket"
        OnAddProduct onAddProduct = new OnAddProduct() {
            @Override
            public void onProductAdded() {
                updateCounter();

                Toast.makeText(getContext(), "Product successfully added to Basket", Toast.LENGTH_SHORT).show();
            }
        };

        //SetAdapter of BestPriceAdapter
        bestPriceAdapter = new BestPriceAdapter((Context) getActivity(), getDammyDataBestPrice(), onAddProduct);
        recyclerViewBestPrice.setAdapter(bestPriceAdapter);
        recyclerViewBestPrice.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        //Call Layout of BasketActivity
        imageViewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BasketActivity.class);
                startActivity(intent);
            }
        });
        // Call Layout of Account
        imageViewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityLogin.class);
                startActivity(intent);
            }
        });


        updateCounter();

        imageViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout(v);
                Toast.makeText(getContext(), "You have successfully logged out!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }

    // Process of Update Counter of TextCounter on Basket in Layout of HomeFragment
    public void updateCounter() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final int sum = sportShopDatabase.productDao().getSumQuantity();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewCounter.setText(sum + "");
                    }
                });
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        updateCounter();
    }

    // Our Products of BestPrice on HomeFragment
    public List<Product> getDammyDataBestPrice() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        product1.setImageUrl("https://canary.contestimg.wish.com/api/webimage/59869605669932707c9d87ac-large.jpg?cache_buster=586ac14019a352d78236b26aea8a5bd2");
        product2.setImageUrl("https://img01.ztat.net/article/spp-media-p1/1784880814413ccdbfdd42d31eea6e26/3d22819ba4f44429b2cdcd1471e714bd.jpg?imwidth=1800");
        product3.setImageUrl("https://img01.ztat.net/article/spp-media-p1/946c64b1327930bcb9eb23f93f7d998a/0b04244fa0ca4c76923970b23064f109.jpg?imwidth=1800");
        product4.setImageUrl("https://img01.ztat.net/article/spp-media-p1/955fdd882bcb3a13bf2c00718252f278/0db5f89b7b46434eb166143ff7616dc4.jpg?imwidth=762");
        product5.setImageUrl("https://images-eu.ssl-images-amazon.com/images/G/03/AMAZON-FASHION/2020/FASHION/FLIP/09/NOVEMBER/MERCH/KIDS/GW/GW_DESKTOP_CARD_379x304_B_BOOTS._SY304_CB417582744_.jpg");
        product6.setImageUrl("https://mosaic04.ztat.net/vgs/media/outfit-image-mhq/75cd2b79b0b6343f8978eb17a8c8423f/6cab1e0779664fdeb19a781407333cb8.jpg?imwidth=1000");
        product1.setName(" Sport Jersey Team \n Cycling Clothing");
        product2.setName(" Even&Odd\nT-Shirt print");
        product3.setName("HIGH MOM ECHO\n-Jeans Tapered Fit");
        product4.setName("BRUSHED PLAID \nCOAT-Wollmantel");
        product5.setName("  SAAT-1005-");
        product6.setName("PEED-Tights");
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

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getContext(), ActivityLogin.class));
    }


}
