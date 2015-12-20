package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class HomeworkManageActivity extends Activity implements View.OnClickListener{

    private ListView mListView = null;
    private HomeworkViewAdapter mAdapter = null;
    private Button btn_next;
    private ImageView img_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_manage_activity);

        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.homeworkList);
        mListView.setDivider(null);
        mAdapter = new HomeworkViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mAdapter.addItem(
                "2015-07-11",
                "2학년 5반 학생들 내일 시험이 있으니 컴퓨터용 싸인펜 꼭 챙겨오세요!!");
        mAdapter.addItem(
                "2015-07-12",
                "내일은 아침 조회가 있는 날이니 평소보다 좀더 일찍 다니세요 - 담임");
        mAdapter.addItem(
                "2015-07-13",
                "내일 모바일 콘테츠 경진대회 마감이 있습니다. 꼭 시간준수해 주세요.");
        mAdapter.addItem(
                "2015-07-15",
                "모두 HiSchool 가입하지 않은 반 친구가 있다면 꼭좀 초대해서 알려주세요.");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                HomeworkData mData = mAdapter.mListData.get(position);
                Toast.makeText(HomeworkManageActivity.this, mData.content, Toast.LENGTH_SHORT).show();
            }
        });

        btn_next = (Button)findViewById(R.id.btn_confirm);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_confirm:
                Intent it = new Intent(HomeworkManageActivity.this, HomeworkUploadActivity.class);
                startActivity(it);
                break;

            case R.id.img_prev:
                finish();
                break;
        }
    }
}

