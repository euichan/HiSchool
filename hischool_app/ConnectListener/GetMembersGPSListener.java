package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parkjaemin on 2015. 7. 9..
 */
public class GetMembersGPSListener implements Response.Listener {

    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;
    private static GetMembersGPSListener mInstance;

    public static GetMembersGPSListener getInstance(Context context)
    {
        if(mInstance == null)
            return mInstance = new GetMembersGPSListener(context);
        else
            return mInstance;
    }

    public GetMembersGPSListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        JSONObject rep = (JSONObject)response;
        Log.d("GetMembersGPS", response.toString());
        try {
            JSONArray result = rep.getJSONArray("results");
            for(int i=0;i<result.length();i++)
            {
                JSONObject obj = result.getJSONObject(i);
                Map<String,Double> memberXY = new HashMap<String, Double>();
                memberXY.put("x", obj.getDouble("x"));
                memberXY.put("y", obj.getDouble("y"));
                mMap.put(obj.getString("name"), memberXY);
                Log.d("mMAP", mMap.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> getHashMap()
    {
        return mMap;
    }

}
