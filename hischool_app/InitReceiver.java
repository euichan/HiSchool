package com.mobile.sunrin.hischool;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class InitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Init Receiver OnReceiver=========", "SUCCESS!!");
        DataManager.getInstance(context).removePreferences("isHome");
    }

}