package com.gelsin.android;

import com.gelsin.android.util.ResultHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class GelsinActions {

    public static void getNearbyShops(double latitude, double longitude, ResultHandler resultHandler) {
        RequestParams params = new RequestParams();
        params.put("distance", 1000);
        params.put("latitude", latitude);
        params.put("longitude", longitude);

        Gelsin.client.get("shop/near", params, resultHandler);

    }

}
