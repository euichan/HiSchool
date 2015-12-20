package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class MenuTeacherActivity extends Activity implements View.OnClickListener {

    ImageView goSchool, goLate, goCall, goHomework, goMessage, goSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_teacher_activity);

        goSchool = (ImageView)findViewById(R.id.btn_goSchool);
        goSchool.setOnClickListener(this);

        goLate = (ImageView)findViewById(R.id.btn_goLater);
        goLate.setOnClickListener(this);

        goCall = (ImageView)findViewById(R.id.btn_goCall);
        goCall.setOnClickListener(this);

        goHomework= (ImageView)findViewById(R.id.btn_goHomework);
        goHomework.setOnClickListener(this);

        goMessage= (ImageView)findViewById(R.id.btn_goMessage);
        goMessage.setOnClickListener(this);

        goSetting= (ImageView)findViewById(R.id.btn_goSetting);
        goSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_goSchool:
                Intent it1 = new Intent(MenuTeacherActivity.this, TeacherMapActivity.class);
                startActivity(it1);
                break;
            case R.id.btn_goLater:

                Intent it2 = new Intent(MenuTeacherActivity.this, LaterCheckActivity.class);
                startActivity(it2);
                break;
            case R.id.btn_goCall:
                Intent it3 = new Intent(MenuTeacherActivity.this, StudentsCallActivity.class);
                startActivity(it3);
                break;
            case R.id.btn_goHomework:
                Intent it4 = new Intent(MenuTeacherActivity.this, HomeworkManageActivity.class);
                startActivity(it4);
                break;
            case R.id.btn_goMessage:
                Intent it5 = new Intent(MenuTeacherActivity.this, MessageActivity.class);
                startActivity(it5);
                break;
            case R.id.btn_goSetting:
                //Intent it6 = new Intent(MenuTeacherActivity.this, TeacherMapActivity.class);
                //startActivity(it6);
                Intent it6 = new Intent(MenuTeacherActivity.this, SettingActivity.class);
                startActivity(it6);
                break;
        }
    }
}
