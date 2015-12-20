package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by parkjaemin on 2015. 7. 13..
 */
public class SignUpFinalActivity extends Activity implements View.OnClickListener{

    ImageView img_prev, img_confirm;
    EditText input_name, input_account;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_final_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);

        img_confirm = (ImageView)findViewById(R.id.img_confirm);
        img_confirm.setOnClickListener(this);

        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);

        input_name = (EditText)findViewById(R.id.input_name);
        input_account = (EditText)findViewById(R.id.input_accout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_prev:
                finish();
                break;
            case R.id.img_confirm:
            case R.id.btn_confirm:
                if(input_name.getText().toString().equals("") ||
                        input_account.getText().toString().equals("")) {
                    Toast.makeText(
                            SignUpFinalActivity.this,
                            "해당 사항들은 필수사항입니다.\n 다시 채워주세요.",
                            Toast.LENGTH_SHORT).show();
                    break;
                }


                String dialog = DataManager.getInstance(null).getPreferences("sign_dialog");
                String pwd = DataManager.getInstance(null).getPreferences("sign_pwd");
                String stu = DataManager.getInstance(null).getPreferences("student");
                String gcm = DataManager.getInstance(null).getPreferences("device");
                String name = input_name.getText().toString();
                String account = input_account.getText().toString();

                Log.d("asdf",dialog);
                //SignUp Connect


                StringRequest req = ConnectManager.getInstance().signUp(getApplicationContext(),
                        dialog,
                        pwd,
                        name,
                        Integer.parseInt(account),
                        stu,
                        gcm);

                VolleyManager.getInstance(getApplicationContext()).getRequestQueue().add(req);

                Toast.makeText(SignUpFinalActivity.this, "회원가입에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(SignUpFinalActivity.this, MenuStudentActivity.class);
                startActivity(it);
                MainActivity.mInstance.finish();
                finish();
                break;
        }
    }
}
