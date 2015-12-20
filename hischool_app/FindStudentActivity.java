package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 6. 11..
 */
public class FindStudentActivity extends Activity {

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    ArrayList<ListData> arraylist = new ArrayList<ListData>();
    String[] rank;
    String[] country;
    String[] population;
    EditText editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_student_activity);

        mListView = (ListView) findViewById(R.id.student_list);

        rank = new String[] { "1번", "2번", "3번", "4번", "5번", "6번", "7번", "8번", "9번", "10번" };

        country = new String[] { "박재민", "김도현", "전의찬",
                "고유진", "서백민", "김승엽", "박건후", "강전완",
                "강상혁", "이은서" };

        population = new String[] { "등교완료", "11km",
                "13km", "등교준비", "등교준비", "등교완료",
                "35km", "21km", "등교준비", "등교완료" };

        for (int i = 0; i < rank.length; i++)
        {
            ListData ld = new ListData(rank[i], country[i],
                    population[i]);
            // Binds all strings into an array
            arraylist.add(ld);
        }

        mAdapter = new ListViewAdapter(this, arraylist);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                TextView choice_state = (TextView)findViewById(R.id.student_all_state);
                ListData mData = mAdapter.mListData.get(position);
                Toast.makeText(FindStudentActivity.this, mData.mName + " : " + mData.mState, Toast.LENGTH_SHORT).show();
            }
        });

        editsearch = (EditText) findViewById(R.id.student_search);

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
}
