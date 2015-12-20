package com.mobile.sunrin.hischool;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by parkjaemin on 2015. 7. 12..
 */
public class MyMenuWidget extends AppWidgetProvider {

    private final String TAG = "MyAppWidget";
    private Context context;

    @Override
    public void onEnabled(Context context)
    {
        Log.d(TAG, "=====================onEnabled========================");
        super.onEnabled(context);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        Log.i(TAG, "======================= onUpdate() =======================");

        this.context = context;

        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for(int i=0; i<appWidgetIds.length; i++){
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_bob_activity);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.i(TAG, "======================= onDeleted() =======================");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        Log.i(TAG, "======================= onDisabled() =======================");
        super.onDisabled(context);
    }

    /**
     * UI 설정 이벤트 설정
     */
    public void initUI(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG, "======================= initUI() =======================");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_menu_activity);

        Intent eventIntent              = new Intent(Const.ACTION_EVENT);

        PendingIntent eventPIntent          = PendingIntent.getBroadcast(context, 0, eventIntent        , 0);

        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    /**
     * Receiver 수신
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        Log.d(TAG, "onReceive() action = " + action);

        // Default Recevier
        if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)){

        }
        else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)){
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            initUI(context, manager, manager.getAppWidgetIds(new ComponentName(context, getClass())));
        }
        else if(AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)){

        }
        else if(AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)){

        }

        // Custom Recevier
        else if(Const.ACTION_EVENT.equals(action)){
            Toast.makeText(context, "Receiver 수신 완료!!.", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(context, StudentMapActivity.class);
            context.startActivity(intent2);
        }
    }

    /**
     * Activity 호출 (Intent.FLAG_ACTIVITY_NEW_TASK)
     */
    private void callActivity(Context context){
        Log.d(TAG, "callActivity()");
        Intent intent = new Intent("arabiannight.tistory.com.widget.CALL_ACTIVITY");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private class Const {

        public static final String  ACTION_EVENT = "com.mobile.sunrin.hischool.widget.ACTION_EVENT";

    }
}

