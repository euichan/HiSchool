package com.mobile.sunrin.hischool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class HomeworkViewAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<HomeworkData> mListData = new ArrayList<HomeworkData>();

    public HomeworkViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

        public TextView mDate;
        public TextView content;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.homework_manage_item, null);

            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.mDate = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        HomeworkData mData = mListData.get(position);

        holder.content.setText(mData.content);
        holder.mDate.setText(mData.date);

        return convertView;
    }

    public void addItem(String date, String content){
        HomeworkData addInfo = null;
        addInfo = new HomeworkData(date, content);

        mListData.add(addInfo);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, HomeworkData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}