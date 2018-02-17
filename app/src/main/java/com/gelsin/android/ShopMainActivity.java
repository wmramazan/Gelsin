package com.gelsin.android;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class ShopMainActivity extends AppCompatActivity {

    private final String TAG = "ShopMainActivity";

    ActionBar actionBar;
    BottomNavigationView navigationView;
    ShopOrdersFragment shopOrdersFragment;
    ProductsFragment productsFragment;
    FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_orders:
                    if(null == shopOrdersFragment)
                        shopOrdersFragment = new ShopOrdersFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, shopOrdersFragment).commit();
                    break;
                case R.id.navigation_products:
                    if(null == productsFragment)
                        productsFragment = new ProductsFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, productsFragment).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.shop_management);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_gelsin);
        actionBar.setElevation(3);

        shopOrdersFragment = new ShopOrdersFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.shop_main_container, shopOrdersFragment).commit();

        navigationView = (BottomNavigationView) findViewById(R.id.shop_main_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
}
