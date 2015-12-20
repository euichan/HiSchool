package com.mobile.sunrin.hischool;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by parkjaemin on 2015. 6. 11..
 */
class ListData {
    public String mNum;

    public String mName;

    public String mState;

    public ListData(String num, String name,String state)
    {
        this.mNum = num;
        this.mName = name;
        this.mState = state;
    }

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(ListData mListDate_1, ListData mListDate_2) {
            return sCollator.compare(mListDate_1.mNum, mListDate_2.mNum);
        }
    };
}
