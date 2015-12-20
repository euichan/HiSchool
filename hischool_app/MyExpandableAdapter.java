package com.mobile.sunrin.hischool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {
    Context context;
    List<ParentObject> parentObjects;

    public MyExpandableAdapter(Context context, List<ParentObject> parentObjects)
    {
        this.context = context;
        this.parentObjects = parentObjects;
    }
    @Override
    public int getGroupCount() {
        return parentObjects.size();
    }

    //Add 2 to childcount. The first row and the last row are used as header and footer to childview
    @Override
    public int getChildrenCount(int i) {
        return parentObjects.get(i).childObjects.size() +2;
    }

    @Override
    public ParentObject getGroup(int i) {
        return parentObjects.get(i);
    }

    @Override
    public ChildObject getChild(int i, int i2) {
        return parentObjects.get(i).childObjects.get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
    {
        ParentObject currentParent = parentObjects.get(i);
        if(view ==null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.late_check_parent_list_row,null);
        }
        TextView txtYear = (TextView)view.findViewById(R.id.txtYear);
        TextView txtMonth = (TextView)view.findViewById(R.id.txtMonth);
        txtYear.setText(currentParent.year);
        txtMonth.setText(currentParent.month);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ParentObject currentParent = getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //the first row is used as header
        if(childPosition ==0)
        {
            view = inflater.inflate(R.layout.late_check_header, null);
            TextView txtHeader = (TextView)view.findViewById(R.id.txtHeader);
            txtHeader.setText(currentParent.textToHeader);
        }

        //Here is the ListView of the ChildView
        if(childPosition>0 && childPosition<getChildrenCount(groupPosition)-1)
        {
            ChildObject currentChild = getChild(groupPosition,childPosition-1);
            view = inflater.inflate(R.layout.late_check_child_list_row,null);
            TextView txtName = (TextView)view.findViewById(R.id.txtName);
            ImageView imgProfile = (ImageView)view.findViewById(R.id.imgProfile);
            txtName.setText(currentChild.childName);
            imgProfile.setImageResource(R.drawable.icon);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}