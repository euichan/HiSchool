package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class SettingActivity extends Activity implements View.OnClickListener{

    ImageView imgPrev;
    TextView goSchool, push, logout;
    CheckBox chk_goSchool, chk_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        imgPrev = (ImageView)findViewById(R.id.img_prev);
        imgPrev.setOnClickListener(this);

        goSchool  = (TextView)findViewById(R.id.goSchool);
        push = (TextView)findViewById(R.id.push);
        chk_goSchool = (CheckBox)findViewById(R.id.check_goSchool);
        chk_push = (CheckBox)findViewById(R.id.check_push);
        logout = (TextView)findViewById(R.id.logout);
        goSchool.setOnClickListener(this);
        push.setOnClickListener(this);
        chk_goSchool.setOnClickListener(this);
        chk_push.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.img_prev:
                finish();
                break;

            case R.id.goSchool:
                if(chk_goSchool.isChecked())
                    chk_goSchool.setChecked(false);
                else
                    chk_goSchool.setChecked(true);
                break;

            case R.id.push:
                if(chk_push.isChecked())
                    chk_push.setChecked(false);
                else
                    chk_push.setChecked(true);
                break;

            case R.id.logout:
                MenuStudentActivity.mInstance.finish();

                Intent it = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(it);
                finish();
                break;
        }
    }
}
