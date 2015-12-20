package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 6. 18..
 */
public class PublicKeyListener implements Response.Listener {

    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;
    private static PublicKeyListener mInstance;

    public static PublicKeyListener getInstance(Context context)
    {
        if(mInstance != null)
            return mInstance;
        else
            return mInstance = new PublicKeyListener(context);
    }

    public PublicKeyListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        JSONObject rep = (JSONObject)response;
        Log.d("Login", response.toString());
        try {
            mMap.put("public_key",rep.getString("public_key"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("public_key", (String)mMap.get("public_key"));
    }

    public HashMap<String, Object> getHashMap()
    {
        return mMap;
    }

}