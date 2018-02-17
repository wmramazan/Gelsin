package com.gelsin.android.item;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class OrderItem {

    ArrayList<ProductItem> products;
    String _id, customer_id, date;
    CustomerItem customer;
    int status;

    public OrderItem(ArrayList<ProductItem> products, String _id, String customer_id, String date, CustomerItem customer, int status) {
        this.products = products;
        this._id = _id;
        this.customer_id = customer_id;
        this.date = date;
        this.customer = customer;
        this.status = status;
    }

    public ArrayList<ProductItem> getProducts() {
        return products;
    }

    public String get_id() {
        return _id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getDate() {
        return date;
    }

    public CustomerItem getCustomer() {
        return customer;
    }

    public int getStatus() {
        return status;
    }
}
