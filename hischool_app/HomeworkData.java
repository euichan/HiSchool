package com.mobile.sunrin.hischool;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by parkjaemin on 2015. 7. 15..
 */
public class HomeworkData {
    public String date;

    public String content;

    public HomeworkData(String date, String content)
    {
        this.date = date;
        this.content = content;
    }

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<HomeworkData> ALPHA_COMPARATOR = new Comparator<HomeworkData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(HomeworkData mListDate_1, HomeworkData mListDate_2) {
            return sCollator.compare(mListDate_1.date, mListDate_2.date);
        }
    };
}
