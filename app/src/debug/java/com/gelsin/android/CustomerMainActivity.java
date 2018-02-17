package com.gelsin.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class CustomerMainActivity extends AppCompatActivity {

    ActionBar actionBar;
    BottomNavigationView navigationView;
    MapFragment mapFragment;
    ShopsFragment shopsFragment;
    OrdersFragment ordersFragment;
    FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    if(null == mapFragment)
                        mapFragment = new MapFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, mapFragment).commit();
                    break;
                case R.id.navigation_shops:
                    if(null == shopsFragment)
                        shopsFragment = new ShopsFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, shopsFragment).commit();
                    break;
                case R.id.navigation_orders:
                    if(null == ordersFragment)
                        ordersFragment = new OrdersFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, ordersFragment).commit();
                    break;
                case R.id.navigation_profile:
                    // TODO: 17.02.2018 Add Intent to switch user.
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_gelsin);
        actionBar.setElevation(3);

        mapFragment = new MapFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.customer_main_container, mapFragment).commit();

        navigationView = (BottomNavigationView) findViewById(R.id.customer_main_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
