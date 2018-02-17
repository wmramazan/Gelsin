package com.gelsin.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gelsin.android.adapter.ShopListAdapter;
import com.gelsin.android.item.ShopItem;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CustomerMainActivity extends AppCompatActivity {

    private final String TAG = "CustomerMainActivity";

    ActionBar actionBar;
    BottomNavigationView navigationView;
    MapFragment mapFragment;
    ShopsFragment shopsFragment;
    OrdersFragment ordersFragment;
    ProfileFragment profileFragment;
    FragmentManager fragmentManager;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;
    LatLng location;

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
                    if(null == profileFragment)
                        profileFragment = new ProfileFragment();
                    fragmentManager.beginTransaction().replace(R.id.customer_main_container, profileFragment).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            mapFragment.startLocationService();
        else
            Toast.makeText(this, getString(R.string.no_permission), Toast.LENGTH_LONG);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(null == receiver) {
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    if(intent.getAction().equals(LocationService.LOCATION)) {

                        location = new LatLng(
                                intent.getExtras().getDouble(LocationService.LOCATION_LATITUDE),
                                intent.getExtras().getDouble(LocationService.LOCATION_LONGITUDE)
                        );

                        mapFragment.setLocation(location);
                        
                    } else if(intent.getAction().equals(LocationService.PLACE))
                        mapFragment.setPlace(intent.getExtras().getString(LocationService.PLACE));

                }
            };
        }

        intentFilter = new IntentFilter();
        intentFilter.addAction(LocationService.LOCATION);
        intentFilter.addAction(LocationService.PLACE);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null != receiver) unregisterReceiver(receiver);
    }

    public void goToShopMainActivity(View view) {
        startActivity(new Intent(this, ShopMainActivity.class));
    }
}
