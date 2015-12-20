package com.mobile.sunrin.hischool;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by parkjaemin on 2015. 5. 30..
 */
public class VolleyManager {
    private static VolleyManager mInstance = null;
    private RequestQueue mRequestQueue;

    private VolleyManager(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }
    public static VolleyManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleyManager(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }
}
