package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 6. 18..
 */
public class JoinListener implements Response.Listener {

    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;

    public JoinListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        JSONObject rep = (JSONObject)response;
        Log.d("SchoolMeal", response.toString());
        if(rep != null)
            Log.d("SchoolMeal", rep.toString());
    }

}
