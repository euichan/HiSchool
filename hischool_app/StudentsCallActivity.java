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
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class StudentsCallActivity extends Activity implements View.OnClickListener{
    private ListView mListView = null;
    private MyCallAdapter mAdapter = null;
    ArrayList<CallData> arraylist = new ArrayList<CallData>();
    ImageView img_prev;
    Image[] profile;
    String[] name;
    String[] phone;
    EditText editsearch;
    int[] resId;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_call_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.callList);
        mListView.setDivider(null);

        resId = new int[] { R.drawable.design, R.drawable.app, R.drawable.server, R.drawable.web};

        CallData ld = new CallData(R.drawable.icon, "[교사] 김은주", "01000000000");
        CallData ld1 = new CallData(resId[0], "고유진","01031121324");
        CallData ld2 = new CallData(resId[1], "박재민","01026373680");
        CallData ld3 = new CallData(resId[2], "김도현","01029255572");
        CallData ld4 = new CallData(resId[3], "전의찬","01088228445");

        arraylist.add(ld);
        arraylist.add(ld1);
        arraylist.add(ld3);
        arraylist.add(ld2);
        arraylist.add(ld4);

        mAdapter = new MyCallAdapter(this, arraylist);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                CallData mData = mAdapter.mListData.get(position);
                Toast.makeText(StudentsCallActivity.this, mData.mName  + ": " + mData.mCallNum, Toast.LENGTH_SHORT).show();
                Intent it = new Intent(Intent.ACTION_CALL);
                it.setData(Uri.parse("tel:" + mData.mCallNum));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
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
        switch (v.getId())
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
            pd = new ProgressDialog(StudentsCallActivity.this);
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
