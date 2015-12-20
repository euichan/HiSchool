package com.mobile.sunrin.hischool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by parkjaemin on 2015. 6. 18..
 */
public class DBManager {

    public static DBManager mInstance;
    public MySQLiteHelper helper;
    public SQLiteDatabase db;
    public static boolean state = false;

    public static DBManager getInstance(){
        if(mInstance == null){
            mInstance = new DBManager();
            state = true;
        }
        return mInstance;
    }

    public void initHelper(Context context)
    {
        if(helper == null) {
            helper = new MySQLiteHelper(context, "schoolmeal.db", null, 1);
            Log.d("Init Helper Success", "GOODD!!!");
        }
        else {
            return;
        }

    }

    public void insert(int num, String meal)
    {
        String table = MySQLiteHelper.getCurrentTableName();
        db = helper.getWritableDatabase();
        Log.d("Insert DB Values", meal);
        ContentValues values = new ContentValues();
        values.put("day", num);
        values.put("meal", meal);
        if(select(num)!=null)
            delete(num);
        db.insert(table, null, values);
        Log.d("Insert " + num + "Success", meal);
    }

    public void update(int target, String text)
    {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("text", text);
        db.update(MySQLiteHelper.getCurrentTableName(),
                values,
                "num=?",
                new String[]{String.valueOf(target)});
        Log.d("Update " + target + "Success", text);
    }

    public void delete(int target)
    {
        db = helper.getWritableDatabase();
        db.delete(MySQLiteHelper.getCurrentTableName(),
                "day=?",
                new String[]{String.valueOf(target)});
        Log.d("SQLITE DELETE", String.valueOf(target));
    }

    public String select(int target)
    {
        db = helper.getReadableDatabase();
        Cursor c = db.query(MySQLiteHelper.getCurrentTableName(),
                null,
                null,
                null,
                null,
                null,
                null);
        String text = "";

        while(c.moveToNext())
        {
            int num = c.getInt(c.getColumnIndex("day"));

            if(num == target)
            {
                text = c.getString(c.getColumnIndex("meal"));
                Log.d("SQL SELECT", target + ": " + String.valueOf(text));
                break;
            }
        }
        return text;

    }


}
