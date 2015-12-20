package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class SignUpMainActivity extends Activity implements View.OnClickListener{

    private ImageView img_prev, img_next;
    private Button btn_student,btn_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);

        btn_student = (Button)findViewById(R.id.btn_student);
        btn_teacher = (Button)findViewById(R.id.btn_teacher);

        btn_student.setOnClickListener(this);
        btn_teacher.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.img_prev:
                finish();
                break;

            case R.id.btn_student:
                Intent it1 = new Intent(SignUpMainActivity.this, SignUpFirstActivity.class);
                startActivity(it1);
                DataManager.getInstance(null).savePreferences("student", "T");
                finish();
                break;

            case R.id.btn_teacher:
                Intent it2 = new Intent(SignUpMainActivity.this, SignUpFirstActivity.class);
                startActivity(it2);
                DataManager.getInstance(null).savePreferences("student", "F");
                finish();
                break;
        }
    }
}
