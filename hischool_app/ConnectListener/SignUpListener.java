package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 7. 13..
 */
public class SignUpListener implements Response.Listener {

    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;
    private static SignUpListener mInstance;

    public static SignUpListener getInstance(Context context)
    {
        if(mInstance != null)
            return mInstance = new SignUpListener(context);
        else
            return mInstance;
    }

    public SignUpListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("SignUp", response.toString());
        if(response instanceof JSONObject) {
            JSONObject rep = (JSONObject) response;
        }

    }

    public HashMap<String, Object> getHashMap()
    {
        return mMap;
    }

}
