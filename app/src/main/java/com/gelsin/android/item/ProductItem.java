package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ProductItem {

    String id, name;
    float price;

    public ProductItem(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
