package com.gelsin.android;

import android.util.Log;
import android.widget.Toast;

import com.gelsin.android.util.ResultHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class GelsinHttpClient {

    private final String BASE_URL = "http://api.gelsin.com/";
    private final String TAG = "GelsinHttpClient";

    AsyncHttpClient client = new AsyncHttpClient();

    public GelsinHttpClient() {
        client.setCookieStore(new PersistentCookieStore(Gelsin.get()));
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(BASE_URL + url, params, responseHandler);
    }

    public void post(String url, RequestParams params, final ResultHandler resultHandler) {
        // TODO: 6.09.2017 Use new method with some requests.
        client.post(BASE_URL + url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // TODO: 17.02.2018 Handle response
                Log.d(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // TODO: 17.02.2018 Check Internet Connection
                Toast.makeText(Gelsin.get(), R.string.noInternet, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
