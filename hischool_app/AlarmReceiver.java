package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by parkjaemin on 2015. 7. 12..
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("OnReceiver", "SUCCESS!!");

        if(DataManager.getInstance(null).getPreferences("isHome").equals("false") ||
                !DataManager.getInstance(null).getPreferences("isHome").equals(""))
            return;

        final NotificationManager mNm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(context, StudentMapActivity.class);

        PendingIntent pi = PendingIntent.getActivity(context,
                0x000,
                intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);


        final Notification mNoti = new NotificationCompat.Builder(context)
                .setContentTitle("HiSchool")
                .setContentText("등교를 시작하시겠습니까?")
                .setSmallIcon(R.drawable.icon)
                .setTicker("등교하세요!!")
                .setAutoCancel(true)
                .setContentIntent(pi)
                .build();

        boolean[] week = {false,true,true,true,true,true,true,true};

        Calendar cal = Calendar.getInstance();

        if(!week[cal.get(Calendar.DAY_OF_WEEK)])
            return;

        MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
                mNm.notify(777, mNoti);
            }
        });

        try
        {
            player.prepare();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
