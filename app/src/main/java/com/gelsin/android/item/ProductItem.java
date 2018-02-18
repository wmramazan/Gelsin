package com.gelsin.android.item;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ProductItem {

    String _id, name, shop;
    float price;
    int quantity;

    public ProductItem(String _id, String name, String shop, float price) {
        this.quantity = 0;
        this._id = _id;
        this.name = name;
        this.shop = shop;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getShop() {
        return shop;
    }

    public float getPrice() {
        return price;
    }

    public void increaseQuantity() {
        if(name.contains(quantity + " x "))
            name = name.replace(quantity + " x ", "");
        name = ++quantity + " x " + name;
    }

    public void decreaseQuantity() {
        if(quantity == 0)
            return;
        if(name.contains(quantity + " x "))
            name = name.replace(quantity + " x ", "");
        name = --quantity + " x " + name;
    }

    public int getQuantity() {
        return quantity;
    }
}
