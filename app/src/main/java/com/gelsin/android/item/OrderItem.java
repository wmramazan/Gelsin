package com.gelsin.android.item;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class OrderItem {

    ArrayList<ProductItem> products;
    String customer_id;
    long date;

    public OrderItem(ArrayList<ProductItem> products, String customer_id, long date) {
        this.products = products;
        this.customer_id = customer_id;
        this.date = date;
    }

    public ArrayList<ProductItem> getProducts() {
        return products;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public long getDate() {
        return date;
    }
}
