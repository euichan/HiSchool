package com.mobile.sunrin.hischool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by parkjaemin on 2015. 6. 18..
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " + getCurrentTableName() +
                " ( day INTEGER PRIMARY KEY, " +
                "meal TEXT NOT NULL );";

        Log.d("SQLITE OnCreate", "Success");
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + getCurrentTableName();
        db.execSQL(sql);
        onCreate(db);
    }

    public static String getCurrentTableName()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        String create_table = "meal" + year+"0"+month;
        return create_table;
    }
}
