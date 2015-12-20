package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by parkjaemin on 2015. 7. 12..
 */
public class SignUpSecondActivity extends Activity implements View.OnClickListener{

    ImageView img_prev, img_next;
    Button btn_next;
    EditText input_dialog, input_passwd,input_passwd_again;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_second_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);

        img_next = (ImageView)findViewById(R.id.img_next);
        img_next.setOnClickListener(this);

        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        input_dialog = (EditText)findViewById(R.id.input_dialog);
        input_passwd = (EditText)findViewById(R.id.input_passwd);
        input_passwd_again = (EditText)findViewById(R.id.input_passwd_again);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_prev:
                Intent it1 = new Intent(SignUpSecondActivity.this, SignUpFirstActivity.class);
                startActivity(it1);
                finish();
                break;
            case R.id.img_next:
            case R.id.btn_next:
                Log.d("SIGNUPSECOND", new String(input_dialog.getText().toString()));
                if(input_dialog.getText().toString().equals("") ||
                        input_passwd.getText().toString().equals("") ||
                        input_passwd_again.getText().toString().equals(""))
                {
                    Log.d("SIGNUPSECOND", new String(input_dialog.getText().toString()));
                    Toast.makeText(SignUpSecondActivity.this, "해당 사항들은 필수사항입니다.\n 다시 채워주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(!input_passwd.getText().toString().equals(input_passwd_again.getText().toString()))
                {
                    Toast.makeText(SignUpSecondActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    break;
                }


                DataManager.getInstance(null).savePreferences("sign_dialog", input_dialog.getText().toString());
                DataManager.getInstance(null).savePreferences("sign_pwd", input_passwd.getText().toString());

                if(DataManager.getInstance(null).getPreferences("student").equals("T"))
                {
                    Intent it = new Intent(SignUpSecondActivity.this, SignUpFinalActivity.class);
                    startActivity(it);
                    finish();
                }
                else
                {
                    Intent it = new Intent(SignUpSecondActivity.this, SignUpTeacherFinalActivity.class);
                    startActivity(it);
                    finish();
                }

                break;
        }
    }
}
