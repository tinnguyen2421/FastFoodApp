package com.example.appfood_by_tinnguyen2421.BottomNavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment.CustomerCartFragment;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment.CustomerHomeFragment;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment.CustomerOrderFragment;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment.CustomerProfileFragment;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment.CustomerTrackFragment;

import com.example.appfood_by_tinnguyen2421.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;


//import com.google.firebase.iid.FirebaseInstanceId;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TextView titletoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_panel__bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        //toolbar = findViewById(R.id.toolbar);
        //titletoolbar = findViewById(R.id.toolbar_title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //titletoolbar.setText("Thực đơn hôm nay");
        navigationView.setOnNavigationItemSelectedListener(this);
        UpdateToken();
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (name != null) {
            if (name.equalsIgnoreCase("Homepage")) {
                loadFragment(new CustomerHomeFragment());
            } else if (name.equalsIgnoreCase("Preparingpage")) {
                loadFragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("Preparedpage")) {
                loadFragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("DeliverOrderpage")) {
                loadFragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("ThankYoupage")) {
                loadFragment(new CustomerHomeFragment());
            }
        } else {
            loadFragment(new CustomerHomeFragment());
        }
    }

    private void UpdateToken() {
        //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isComplete()){
                    String token = task.getResult();
                    FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);



                }
            }
        });
        //String refreshToken = String.valueOf(FirebaseMessaging.getInstance().getToken());
        //Token token = new Token(refreshToken);
        //FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {

            case R.id.Home:
                //titletoolbar.setText("Thực đơn hôm nay");
                fragment = new CustomerHomeFragment();
                break;
            case R.id.Cart:
                //titletoolbar.setText("Giỏ hàng");
                fragment = new CustomerCartFragment();
                break;

            case R.id.Order:
                //titletoolbar.setText("Đơn hàng");
                fragment = new CustomerOrderFragment();
               break;

           case R.id.Track:
               //titletoolbar.setText("Theo dõi đơn hàng");
                fragment = new CustomerTrackFragment();
                break;


            case R.id.Profile:
                //titletoolbar.setText("Thông tin cá nhân");
                fragment = new CustomerProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
