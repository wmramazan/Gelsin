package com.gelsin.android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gelsin.android.item.ShopItem;
import com.gelsin.android.util.ResultHandler;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private final String TAG = "MapFragment";

    private GoogleMap map;
    private View view;
    private Intent intent;
    private TextView place;
    private LatLng position;
    private ArrayList<ShopItem> nearbyShops;

    public MapFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");

        view = inflater.inflate(R.layout.fragment_map, container, false);

        place = view.findViewById(R.id.fragment_map_place);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 0);

        } else
            startLocationService();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //map.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void startLocationService() {
        intent = new Intent(getContext(), LocationService.class);
        getContext().startService(intent);
    }

    public void moveCameraToLocation() {
        map.addMarker(new MarkerOptions().position(position).title(getString(R.string.your_location)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
    }

    public void showNearbyShops() {

        nearbyShops = new ArrayList<>();

        GelsinActions.getNearbyShops(position.latitude, position.longitude, new ResultHandler() {
            @Override
            public void handle(JSONArray result) {

                //Log.d(TAG, String.valueOf(result.length()));

                try {
                    for(int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        JSONArray location = jsonObject.getJSONArray("loc");
                        nearbyShops.add(new ShopItem(
                                jsonObject.getString("name"),
                                jsonObject.getJSONObject("category").getString("name"),
                                location.getDouble(0),
                                location.getDouble(1)
                        ));
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "Exception");
                    e.printStackTrace();
                }

            }
        });

        //Log.d(TAG, String.valueOf(nearbyShops.size()));

        for(ShopItem shop : nearbyShops)
            map.addMarker(
                    new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_restaurant))
                    .title(shop.getName())
                    .snippet(shop.getCategory())
                    .position(new LatLng(shop.getLatitude(), shop.getLongitude()))
            );

    }

    public void setLocation(double latitude, double longitude) {
        position = new LatLng(latitude, longitude);

        moveCameraToLocation();
        showNearbyShops();
    }

    public void setPlace(String place_name) {
        place.setText(place_name);
    }
}
