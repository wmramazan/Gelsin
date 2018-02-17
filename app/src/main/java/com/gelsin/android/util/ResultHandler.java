package com.gelsin.android.util;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by wmramazan on 17.02.2018.
 */

public interface ResultHandler {

    void handle(JSONArray result);

}
