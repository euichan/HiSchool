package com.mobile.sunrin.hischool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by parkjaemin on 2015. 6. 11..
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    public List<ListData> mListData = new ArrayList<ListData>();
    private ArrayList<ListData> arraylist;

    public ListViewAdapter(Context mContext, List<ListData> arrayList) {
        super();
        this.mContext = mContext;
        this.mListData = arrayList;
        this.arraylist = new ArrayList<ListData>();
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
            convertView = inflater.inflate(R.layout.find_student_list_item, null);

            holder.mNum = (TextView) convertView.findViewById(R.id.student_num);
            holder.mName = (TextView) convertView.findViewById(R.id.student_name);
            holder.mState = (TextView) convertView.findViewById(R.id.student_state);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        holder.mNum.setText(mData.mNum);
        holder.mName.setText(mData.mName);
        holder.mState.setText(mData.mState);


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
            for (ListData ld: arraylist)
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
        public TextView mNum;

        public TextView mName;

        public TextView mState;
    }

    public void addItem(String num, String name, String state){
        ListData addInfo = null;
        addInfo = new ListData(num, name, state);

        mListData.add(addInfo);
        dataChange();
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}