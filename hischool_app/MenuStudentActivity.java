package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class MenuStudentActivity extends Activity implements View.OnClickListener {

    ImageView goSchool, goLate, goCall, goHomework, goMessage, goSetting;

    public static MenuStudentActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_student_activity);

        mInstance = MenuStudentActivity.this;

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

        Log.d("SESSION_ID", DataManager.getInstance(null).getPreferences("sessionid"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_goSchool:
                Intent it1 = new Intent(MenuStudentActivity.this, StudentMapActivity.class);
                startActivity(it1);
                break;
            case R.id.btn_goLater:

                Intent it2 = new Intent(MenuStudentActivity.this, LateCheckActivity.class);
                startActivity(it2);
                break;
            case R.id.btn_goCall:
                Intent it3 = new Intent(MenuStudentActivity.this, StudentsCallActivity.class);
                startActivity(it3);
                break;
            case R.id.btn_goHomework:
                Intent it4 = new Intent(MenuStudentActivity.this, HomeworkManageActivity.class);
                startActivity(it4);
                break;
            case R.id.btn_goMessage:
                //Intent it5 = new Intent(MenuTeacherActivity.this, .class);
                //startActivity(it5);
                Toast.makeText(MenuStudentActivity.this, "현재 학생은 지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_goSetting:
                Intent it6 = new Intent(MenuStudentActivity.this, SettingActivity.class);
                startActivity(it6);
                break;
        }
    }
}
