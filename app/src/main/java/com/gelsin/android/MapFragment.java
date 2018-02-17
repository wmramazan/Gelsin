package com.gelsin.android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private View view;
    private Intent intent;
    private TextView place;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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

    public void showNearbyPlaces() {
        startLocationService();
    }

    public void startLocationService() {
        intent = new Intent(getContext(), LocationService.class);
        getContext().startService(intent);
    }

    public void moveCameraToLocation(double latitude, double longitude) {
        LatLng position = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(position).title(getString(R.string.your_location)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
    }

    public void setPlace(String place_name) {
        place.setText(place_name);
    }
}
