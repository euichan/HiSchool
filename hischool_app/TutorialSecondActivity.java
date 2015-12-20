package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class TutorialSecondActivity extends Activity implements View.OnClickListener{

    protected ImageView btn_skip;
    protected Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_second_acitivity);

        btn_skip = (ImageView)findViewById(R.id.btn_skip);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);

        btn_skip.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_skip:
                break;
            case R.id.btn_confirm:
                Intent it = new Intent(TutorialSecondActivity.this, LaterCheckActivity.class);
                startActivity(it);
                break;
        }
    }
}
