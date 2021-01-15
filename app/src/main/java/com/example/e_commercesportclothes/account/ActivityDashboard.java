package com.example.e_commercesportclothes.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.e_commercesportclothes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class ActivityDashboard extends AppCompatActivity {
   FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    }
}
