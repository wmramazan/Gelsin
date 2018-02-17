package com.gelsin.android;

import android.app.Application;

/**
 * Created by wmramazan on 16.02.2018.
 */

public class Gelsin extends Application {

    private static Gelsin instance;
    static GelsinHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        client = new GelsinHttpClient();
    }

    public static synchronized Gelsin get() {
        return instance;
    }
}
