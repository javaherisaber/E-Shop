package com.example.e_commercesportclothes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.e_commercesportclothes.account.ActivityLogin;
import com.example.e_commercesportclothes.database.SportShopDatabase;
import com.example.e_commercesportclothes.fragments.FavoritesFragment;
import com.example.e_commercesportclothes.fragments.HomeFragment;
import com.example.e_commercesportclothes.fragments.OffersFragment;
import com.example.e_commercesportclothes.fragments.SetsFragment;
import com.example.e_commercesportclothes.fragments.SneakerFragment;
import com.example.e_commercesportclothes.listener.OnAddProduct;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {
    // global reference
    TextView textViewCounter;
    SportShopDatabase sportShopDatabase;
    Executor executor;
    ToggleButton toggleButton;





    //attach for fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         // reference
        toggleButton=findViewById(R.id.toggleButton);
        textViewCounter=(TextView)findViewById(R.id.textViewCounter);
        sportShopDatabase=SportShopDatabase.getInstance(this);
        executor=Executors.newSingleThreadExecutor();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new
                HomeFragment()).commit();


    }

    //Add to Basket Codes
    OnAddProduct onAddProduct=new OnAddProduct() {
        @Override
        public void onProductAdded() {


        }
    };






    //my items in button nav
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorite:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_offers:
                            selectedFragment = new OffersFragment();
                            break;
                        case R.id.nav_trainingsset:
                            selectedFragment = new SetsFragment();
                            break;
                        case R.id.nav_shoes:
                            selectedFragment = new SneakerFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;

                }
            };

    @Override
    protected void onResume() {
        super.onResume();
    }
}

