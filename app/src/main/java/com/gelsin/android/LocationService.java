package com.gelsin.android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.Locale;

public class LocationService extends Service implements LocationListener {

    public static final String LOCATION = "current_location";
    private final String TAG = "LocationListener";
    private final int FREQ = 3000;

    private LocationManager locationManager;
    private Geocoder geocoder;
    private Intent intent;
    private String place_name;

    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate");

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                FREQ,
                0,
                this
        );

        geocoder = new Geocoder(LocationService.this, Locale.getDefault());
    }

    @Override
    public void onLocationChanged(Location location) {
        new PlaceAsyncTask().execute(
                location.getLatitude(),
                location.getLongitude()
        );

        intent = new Intent(LOCATION);
        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        sendBroadcast(intent);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != locationManager)
            locationManager.removeUpdates(this);
    }

    class PlaceAsyncTask extends AsyncTask<Double, Void, Void> {

        @Override
        protected Void doInBackground(Double ... args) {
            Address address = null;

            try {
                address = geocoder.getFromLocation(
                        args[0],
                        args[1],
                        1
                ).get(0);
            } catch (NullPointerException nullPointerException) {
                nullPointerException.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            if(null != address) {
                place_name = address.getFeatureName().length() > 4 ? address.getFeatureName() : address.getAddressLine(0);
                String str_address = "";
                for(int i = 0; i <= address.getMaxAddressLineIndex(); i++)
                    str_address += address.getAddressLine(i) + ", ";
                str_address.substring(0, str_address.length() - 2);
                Log.d(TAG, "Address: " + address);
            }

            return null;
        }

    }
}
