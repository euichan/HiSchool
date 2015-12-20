package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;
import com.mobile.sunrin.hischool.LoginActivity;
import com.mobile.sunrin.hischool.MenuStudentActivity;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class UploadHomeworkListener implements Response.Listener {

    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;
    private static LoginListener mInstance;

    public static LoginListener getInstance(Context context)
    {
        if(mInstance != null)
            return mInstance = new LoginListener(context);
        else
            return mInstance;
    }

    public UploadHomeworkListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("Upload Homework", response.toString());

    }

    public HashMap<String, Object> getHashMap()
    {
        return mMap;
    }

}
