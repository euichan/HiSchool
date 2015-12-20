package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by parkjaemin on 2015. 6. 26..
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String PROPERTY_REG_ID = "269372664422";

    // SharedPreferences에 저장할 때 key 값으로 사용됨.
    private static final String PROPERTY_APP_VERSION = "0.01v";
    private static final String TAG = "HISCHOOL";

    public static LoginActivity mInstance;

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
    private EditText input_phone, input_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = this.getApplicationContext();
        mInstance = LoginActivity.this;

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setTextColor(Color.WHITE);
        btn_login.setHintTextColor(Color.WHITE);
        btn_login.setHint(R.string.login);
        btn_login.setOnClickListener(this);

        ImageView iv = (ImageView)findViewById(R.id.imageView);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Bitmap resized = Bitmap.createScaledBitmap(image, 450, 350, true);
        iv.setImageBitmap(resized);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 레이아웃 크기에 이미지를 맞춘다
        iv.setPadding(3, 3, 3, 3);

        input_phone = (EditText)findViewById(R.id.em_edit);
        input_password = (EditText)findViewById(R.id.lock_edit);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_login:
                //login 처리 부분
                String phone = input_phone.getText().toString();
                String password = input_password.getText().toString();
                StringRequest req = ConnectManager.getInstance().login(this, phone, password);
                VolleyManager.getInstance(this).getRequestQueue().add(req);
                break;

        }
    }
}
