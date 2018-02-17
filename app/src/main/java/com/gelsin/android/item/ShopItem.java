package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ShopItem {

    public static final String CATEGORY_RESTAURANT = "restaurant";
    public static final String CATEGORY_MARKET = "market";
    public static final String CATEGORY_BAKERY = "bakery";

    String _id, name, category_name;
    double latitude, longitude, distance;

    public ShopItem(String _id, String name, String category_name, double latitude, double longitude, double distance) {
        this._id = _id;
        this.name = name;
        this.category_name = category_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance() {
        return distance;
    }
}
