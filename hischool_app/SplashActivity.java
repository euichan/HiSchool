package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by parkjaemin on 2015. 3. 14..
 */
public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManager.getInstance(this);

        this.getWindow().getDecorView().setBackgroundResource(R.drawable.splash);

        JsonObjectRequest req = ConnectManager.getInstance().getSchoolMeal(getApplicationContext());
        VolleyManager.getInstance(this).getRequestQueue().add(req);

           JsonObjectRequest req1 = ConnectManager.getInstance().getMembersGPS(this, 1);
            VolleyManager.getInstance(this).getRequestQueue().add(req1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);
    }
}
