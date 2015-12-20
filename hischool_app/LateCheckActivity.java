package com.mobile.sunrin.hischool;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class LateCheckActivity extends Activity implements View.OnClickListener{
    ExpandableListView elv;
    ImageView img_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.late_check_activity);
        LayoutInflater inflater = getLayoutInflater();
        elv = (ExpandableListView)findViewById(R.id.ex_list);
        elv.setOnGroupExpandListener(onGroupExpandListenser);
        elv.setDivider(null);
        MyExpandableAdapter adapter = new MyExpandableAdapter(this, getData());
        elv.setAdapter(adapter);
        img_prev = (ImageView)findViewById(R.id.img_prev);
        img_prev.setOnClickListener(this);
    }

    ExpandableListView.OnGroupExpandListener onGroupExpandListenser = new ExpandableListView.OnGroupExpandListener() {
        int previousGroup =-1;
        @Override
        public void onGroupExpand(int groupPosition) {
            if(groupPosition!= previousGroup)
                elv.collapseGroup(previousGroup);
            previousGroup = groupPosition;
        }
    };

    //Sample data for expandable list view.
    public List<ParentObject> getData()
    {
        List<ParentObject> parentObjects = new ArrayList<ParentObject>();
            parentObjects.add(new ParentObject(String.valueOf(2015), String.valueOf(7) , "2015년 7월 4일",getChildren(3)));
        return parentObjects;
    }

    private List<ChildObject> getChildren(int childCount)
    {
        List<ChildObject> childObjects = new ArrayList<ChildObject>();
//        for (int i =0; i<childCount; i++)
//        {
//            childObjects.add(new ChildObject("김도현", 10 +i ));
//        }
        childObjects.add(new ChildObject("김도현", 10 ));
        childObjects.add(new ChildObject("박재민", 10 ));
        childObjects.add(new ChildObject("전의찬", 10 ));
        return childObjects;
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

