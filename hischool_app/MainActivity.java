package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.facebook.*;
import com.facebook.model.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener{
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String PROPERTY_REG_ID = "269372664422";

    // SharedPreferences에 저장할 때 key 값으로 사용됨.
    private static final String PROPERTY_APP_VERSION = "0.01v";
    private static final String TAG = "HISCHOOL";
    private AlarmManager alarmManager;

    String SENDER_ID = "269372664422";

    GoogleCloudMessaging gcm;
    SharedPreferences prefs;
    Context context;

    String regid;
    String pub_key;
    private TextView mDisplay;

    private Button btn_login;
    private Button btn_signup;
    private Button facebook_login;
    private Button btn_goLogin, btn_goSign;

    public static MainActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = MainActivity.this;
        context = this.getApplicationContext();

        facebook_login = (Button)findViewById(R.id.facebook_login);
        facebook_login.setOnClickListener(this);

        btn_goLogin = (Button)findViewById(R.id.btn_goLogin);
        btn_goLogin.setOnClickListener(this);

        btn_goSign = (Button)findViewById(R.id.btn_goSign);
        btn_goSign.setOnClickListener(this);


        Session.openActiveSession(this,true, new Session.StatusCallback(){
            @Override
            public void call(Session session, SessionState state, Exception exception)
            {
                if(session.isOpened()){
                    Request.executeMeRequestAsync(session, new Request.GraphUserCallback(){
                        @Override
                        public void onCompleted(GraphUser user, Response response)
                        {
                            if(user!=null) {
                            }
                        }
                    });
                }
            }
        });

        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);
            DataManager.getInstance(null).savePreferences("device", regid);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        unRegisterAlarm();
        registerAlarm();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_goLogin:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_goSign:
                Intent intent = new Intent(MainActivity.this, SignUpMainActivity.class);
                startActivity(intent);
                break;

            case R.id.facebook_login:

        }
    }

    private void unRegisterAlarm()
    {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0x001, intent, 0);

        alarmManager.cancel(pIntent);
    }

    public void registerAlarm()
    {
        //Example Preference Code
        DataManager.getInstance(null).savePreferences("alarm_clock", String.valueOf(820));
        long time = Long.valueOf(DataManager.getInstance(null).getPreferences("alarm_clock"));

        //if 8시 20분
        Calendar time1 = Calendar.getInstance();
        time1.set(Calendar.HOUR_OF_DAY, (int)time/100);
        time1.set(Calendar.MINUTE, (int)time%100);
        time1.set(Calendar.SECOND, 0);

        Calendar time2 = Calendar.getInstance();
        time2.set(Calendar.HOUR_OF_DAY, 0);
        time2.set(Calendar.MINUTE, 0);
        time2.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);
        Intent intent1 = new Intent(this, InitReceiver.class);

        long oneDay = 60*60*24 * 1000;
        PendingIntent pt = PendingIntent.getBroadcast(this, 0x0001, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pt1 = PendingIntent.getBroadcast(this, 0x0002, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time1.getTimeInMillis(), oneDay, pt);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time2.getTimeInMillis(), oneDay, pt1);
        Log.d("Alarm Success","SUCCESS!!!!!!!!!!!");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("ICELANCER", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }

        // 앱이 업데이트 되었는지 확인하고, 업데이트 되었다면 기존 등록 아이디를 제거한다.
        // 새로운 버전에서도 기존 등록 아이디가 정상적으로 동작하는지를 보장할 수 없기 때문이다.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context) {
        return getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // 서버에 발급받은 등록 아이디를 전송한다.
                    // 등록 아이디는 서버에서 앱에 푸쉬 메시지를 전송할 때 사용된다.
                    sendRegistrationIdToBackend();

                    // 등록 아이디를 저장해 등록 아이디를 매번 받지 않도록 한다.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if(msg!= null) {
                    DataManager.getInstance(null).savePreferences("device", msg);

                }
            }

        }.execute(null, null, null);
    }

    private void storeRegistrationId(Context context, String regid) {
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regid);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    private void sendRegistrationIdToBackend() {

    }
}
