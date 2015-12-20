package com.mobile.sunrin.hischool.ConnectListener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;
import com.mobile.sunrin.hischool.LoginActivity;
import com.mobile.sunrin.hischool.MenuStudentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 6. 18..
 */
public class LoginListener implements Response.Listener {

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

    public LoginListener(Context context)
    {
        mContext = context;
        mMap = new HashMap<String, Object>();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("Login", response.toString());
        Intent it = new Intent(LoginActivity.mInstance, MenuStudentActivity.class);
        LoginActivity.mInstance.startActivity(it);
        LoginActivity.mInstance.finish();
    }

    public HashMap<String, Object> getHashMap()
    {
        return mMap;
    }

}
