package com.mobile.sunrin.hischool;

import android.media.Image;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class LaterData {
    public int profile;
    public String mName;
    public boolean isChecked;

    public LaterData(int _profile, String _name, boolean isChecked)
    {
        profile = _profile;
        mName = _name;
        this.isChecked = isChecked;
    }

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<LaterData> ALPHA_COMPARATOR = new Comparator<LaterData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(LaterData mListDate_1, LaterData mListDate_2) {
            return sCollator.compare(mListDate_1.mName, mListDate_2.mName);
        }
    };
}
