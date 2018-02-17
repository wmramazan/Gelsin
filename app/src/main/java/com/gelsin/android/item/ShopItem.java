package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ShopItem {

    String name, category;
    double latitude, longitude;

    public ShopItem(String name, String category, double latitude, double longitude) {
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
