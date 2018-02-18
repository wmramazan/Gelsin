package com.gelsin.android;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gelsin.android.util.ResultHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeliveryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Intent intent;
    private TextView info;
    private LatLng destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        intent = getIntent();
        info = findViewById(R.id.delivery_info);

        destination = new LatLng(
                intent.getDoubleExtra("latitude", 0),
                intent.getDoubleExtra("longitude", 0)
        );

        info.setText(intent.getStringExtra("customer_name"));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.delivery_map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.addMarker(new MarkerOptions().position(destination).title(getString(R.string.destination)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 15));
    }

    public void completeOrder(final View view) {
        view.setEnabled(false);
        GelsinActions.completeOrder(intent.getStringExtra("order_id"), new ResultHandler() {
            @Override
            public void handle(String result) {
                view.setEnabled(true);
                finish();
            }
        });
    }
}
