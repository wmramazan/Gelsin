package com.gelsin.android;

import com.gelsin.android.item.ProductItem;
import com.gelsin.android.util.ResultHandler;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class GelsinActions {

    public static final int LIMIT_DISTANCE = 1000;
    public static final String CUSTOMER_ID = "5a880722f36d2866535e368c";
    public static final String SHOP_ID = "5a8802099f9a5628182951b8";

    public static void getNearbyShops(double latitude, double longitude, ResultHandler resultHandler) {
        RequestParams params = new RequestParams();
        params.put("distance", 1000);
        params.put("latitude", latitude);
        params.put("longitude", longitude);

        Gelsin.client.get("shop/near", params, resultHandler);

    }

    public static void getShopProducts(String shop_id, ResultHandler resultHandler) {
        Gelsin.client.get("product/shop/" + shop_id, null, resultHandler);
    }

    public static void getCustomerOrders(ResultHandler resultHandler) {
        Gelsin.client.get("order/customer/" + CUSTOMER_ID, null, resultHandler);
    }

    public static void getShopOrders(ResultHandler resultHandler) {
        Gelsin.client.get("order/shop/" + SHOP_ID, null, resultHandler);
    }

    public static void giveAnOrder(ArrayList<String> products, ResultHandler resultHandler) {
        RequestParams params = new RequestParams();
        params.put("customer", CUSTOMER_ID);
        params.put("products", products);

        Gelsin.client.post("order", params, resultHandler);
    }

}
