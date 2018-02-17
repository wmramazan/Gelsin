package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class CustomerItem {

    String _id, name;
    double latitude, longitude;

    public CustomerItem(String _id, String name, double latitude, double longitude) {
        this._id = _id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
