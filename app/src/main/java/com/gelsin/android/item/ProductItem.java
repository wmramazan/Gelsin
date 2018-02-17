package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ProductItem {

    String _id, name;
    float price;

    public ProductItem(String _id, String name, float price) {
        this._id = _id;
        this.name = name;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
