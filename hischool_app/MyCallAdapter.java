package com.mobile.sunrin.hischool;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class MyCallAdapter  extends BaseAdapter {
    private Context mContext = null;
    public List<CallData> mListData = new ArrayList<CallData>();
    private ArrayList<CallData> arraylist;

    public MyCallAdapter(Context mContext, List<CallData> arrayList) {
        super();
        this.mContext = mContext;
        this.mListData = arrayList;
        this.arraylist = new ArrayList<CallData>();
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
            convertView = inflater.inflate(R.layout.students_call_item, null);

            holder.profile = (ImageView) convertView.findViewById(R.id.profile);
            holder.mName = (TextView) convertView.findViewById(R.id.name);
            holder.call = new Intent(Intent.ACTION_CALL);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Log.d("POSITION==", String.valueOf(position));
        CallData mData = mListData.get(position);

        if(position<=2)
            holder.profile.setImageResource(mData.profile);
        else
            holder.profile.setImageResource(R.drawable.icon);
        holder.mName.setText(mData.mName);
        holder.call.setAction(Intent.ACTION_CALL);
        holder.call.setData(Uri.parse("tel:" + mData.mCallNum));


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
            for (CallData ld: arraylist)
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

        public Intent call;
    }

    public void addItem(int profile, String name, String call){
        CallData addInfo = null;
        addInfo = new CallData(profile, name, call);

        mListData.add(addInfo);
        dataChange();
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, CallData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}