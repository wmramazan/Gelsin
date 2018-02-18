package com.gelsin.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
                    fragmentManager.beginTransaction().replace(R.id.shop_main_container, shopOrdersFragment).commit();
                    break;
                case R.id.navigation_products:
                    if(null == productsFragment)
                        productsFragment = new ProductsFragment();
                    fragmentManager.beginTransaction().replace(R.id.shop_main_container, productsFragment).commit();
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
        actionBar.setSubtitle(GelsinActions.SHOP_NAME);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_gelsin);
        actionBar.setElevation(3);

        shopOrdersFragment = new ShopOrdersFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.shop_main_container, shopOrdersFragment).commit();

        navigationView = (BottomNavigationView) findViewById(R.id.shop_main_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_product:
                startActivity(new Intent(this, ProductActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onResume() {
        if(navigationView.getSelectedItemId() == R.id.navigation_orders) {
            shopOrdersFragment = new ShopOrdersFragment();
            fragmentManager.beginTransaction().replace(R.id.shop_main_container, shopOrdersFragment).commit();
        } else {
            productsFragment = new ProductsFragment();
            fragmentManager.beginTransaction().replace(R.id.shop_main_container, productsFragment).commit();
        }

        super.onResume();
    }
}
