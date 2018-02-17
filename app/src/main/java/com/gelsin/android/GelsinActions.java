package com.gelsin.android;

import com.gelsin.android.util.ResultHandler;
import com.loopj.android.http.RequestParams;

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

    public static void getCustomerOrders() {

    }

    public static void getShopOrders() {

    }

    public static void giveAnOrder() {

    }

}
