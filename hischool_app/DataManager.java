package com.mobile.sunrin.hischool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class DataManager {

    public static final String PREF_NAME = "STELLA_POLY";
    private static DataManager s_instance;
    Context mContext;

    public static DataManager getInstance(Context context) {
        if (s_instance == null) {
            s_instance = new DataManager(context);
        }
        return s_instance;
    }

    private DataManager(Context context)
    {
        this.mContext = context;
    }

    // 값 불러오기
    public String getPreferences(String tag){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(tag, "");
    }

    // 값 저장하기
    public void savePreferences(String tag, String value){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag, value);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public void removePreferences(String tag){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(tag);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    public void removeAllPreferences(){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
