package com.mobile.sunrin.hischool.ConnectListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.mobile.sunrin.hischool.DBManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by parkjaemin on 2015. 5. 30..
 */
public class SchoolMealListener implements Response.Listener
{
    public static boolean state = false;
    Context mContext;
    private HashMap<String, Object> mMap;
    private static SchoolMealListener mInstance;

    public static SchoolMealListener getInstance(Context context)
    {
        if (mInstance != null)
            return mInstance;
        else
            return mInstance = new SchoolMealListener(context);
    }

    public SchoolMealListener(Context context)
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
        DBManager dbManger = DBManager.getInstance();
        dbManger.initHelper(mContext);
        for(int i=1;i<32;i++)
        {
            try {
                JSONArray arr = rep.getJSONArray(String.valueOf(i));
                Log.d("ARRSTRING " + i, arr.toString());
                String meal = arr.toString().substring(1, arr.toString().length()-1);

                meal = meal.replaceAll(",", " ");
                meal = meal.replaceAll("\"","");
                meal = meal.replaceAll(" ",", ");
                dbManger.insert(i, meal);
                Log.d(String.valueOf(i),meal);
                mMap.put(String.valueOf(i), meal);
            } catch (JSONException e) {
                e.printStackTrace();
                mMap.put(String.valueOf(i), "No Data");
                dbManger.insert(i, "No Data");
            }
        }

        Log.d("Map", mMap.toString());

        for(int i=1;i<32;i++)
        {
            dbManger.select(i);
        }
    }

}
