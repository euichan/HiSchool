package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class HomeworkUploadActivity extends Activity implements View.OnClickListener{

    private ImageView img_prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_upload_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.img_prev:
                finish();
                break;
        }
    }
}
