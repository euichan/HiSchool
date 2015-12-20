package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 7. 9..
 */
public class UpdateXYListener implements Response.Listener {

    Context mContext;
    private HashMap<String, Object> mMap;

    public UpdateXYListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        JSONObject rep = (JSONObject)response;
        Log.d("UpdateXY", response.toString());
    }
}
