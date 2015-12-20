package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class LaterCheckActivity extends Activity implements View.OnClickListener{
    private ListView mListView = null;
    private LaterAdapter mAdapter = null;
    ArrayList<LaterData> arraylist = new ArrayList<LaterData>();
    Image[] profile;
    int[] resId;
    String[] name;
    String[] phone;
    EditText editsearch;
    ProgressDialog pd;
    ImageView img_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.late_check_students_activity);

        mListView = (ListView) findViewById(R.id.lateList);
        mListView.setDivider(null);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);
        resId = new int[] { R.drawable.design, R.drawable.app, R.drawable.server, R.drawable.web};
        //profile = new Image[] { "1번", "2번", "3번", "4번", "5번", "6번", "7번", "8번", "9번", "10번" };

        name = new String[] { "박재민", "김도현", "전의찬",
                "고유진", "서백민", "김승엽", "박건후", "강전완",
                "강상혁", "이은서" };

        phone = new String[] { "등교완료", "11km",
                "13km", "등교준비", "등교준비", "등교완료",
                "35km", "21km", "등교준비", "등교완료" };

        LaterData ld1 = new LaterData(resId[0], "고유진",false);
        LaterData ld2 = new LaterData(resId[1], "박재민",false);
        LaterData ld3 = new LaterData(resId[2], "김도현",false);
        LaterData ld4 = new LaterData(resId[3], "전의찬",false);

        arraylist.add(ld1);
        arraylist.add(ld2);
        arraylist.add(ld3);
        arraylist.add(ld4);


        mAdapter = new LaterAdapter(this, arraylist);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                LaterData mData = mAdapter.mListData.get(position);
                Toast.makeText(LaterCheckActivity.this, mData.mName + ": " + mData.isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        editsearch = (EditText) findViewById(R.id.search_member);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                mAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
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

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(LaterCheckActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }



        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            pd.dismiss();
            bmImage.setImageBitmap(result);
        }
    }
}
