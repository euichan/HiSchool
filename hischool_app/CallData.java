package com.mobile.sunrin.hischool;

import android.media.Image;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by parkjaemin on 2015. 7. 14..
 */
public class CallData {

    public int profile;
    public String mName;
    public String mCallNum;

    public CallData(int _profile, String _name, String _callNum)
    {
        profile = _profile;
        mName = _name;
        mCallNum = _callNum;
    }

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<CallData> ALPHA_COMPARATOR = new Comparator<CallData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(CallData mListDate_1, CallData mListDate_2) {
            return sCollator.compare(mListDate_1.mName, mListDate_2.mName);
        }
    };
}
