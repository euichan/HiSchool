package com.mobile.sunrin.hischool;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class LaterAdapter extends BaseAdapter {
    private Context mContext = null;
    public List<LaterData> mListData = new ArrayList<LaterData>();
    private ArrayList<LaterData> arraylist;

    public LaterAdapter(Context mContext, List<LaterData> arrayList) {
        super();
        this.mContext = mContext;
        this.mListData = arrayList;
        this.arraylist = new ArrayList<LaterData>();
        this.arraylist.addAll(mListData);

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.late_check_students_item, null);

            holder.profile = (ImageView) convertView.findViewById(R.id.profile);
            holder.mName = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        LaterData mData = mListData.get(position);

        holder.profile.setImageResource(mData.profile);
        holder.mName.setText(mData.mName);

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mListData.clear();
        if (charText.length() == 0) {
            mListData.addAll(arraylist);
        }
        else
        {
            for (LaterData ld: arraylist)
            {
                if (ld.mName.toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mListData.add(ld);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        public ImageView profile;

        public TextView mName;

    }

    public void addItem(int profile, String name, boolean isChecked){
        LaterData addInfo = null;
        addInfo = new LaterData(profile, name, isChecked);

        mListData.add(addInfo);
        dataChange();
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, LaterData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}