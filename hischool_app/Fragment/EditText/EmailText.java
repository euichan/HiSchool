package com.mobile.sunrin.hischool.Fragment.EditText;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.mobile.sunrin.hischool.R;

/**
 * Created by parkjaemin on 2015. 3. 14..
 */
public class EmailText extends EditText{

    private boolean mMagnifyingGlassShown = true;
    private Drawable mMagnifyingGlass;
    private Drawable drawable2;
    BitmapDrawable drawable;

    public EmailText(Context context, AttributeSet attrs) {
        super(context,attrs);
        mMagnifyingGlass = getCompoundDrawables()[0];
        drawable2 = getCompoundDrawables()[3];
        this.setTextColor(Color.WHITE);
        this.setHintTextColor(Color.WHITE);


        //drawable = (BitmapDrawable)getResources().getDrawable(R.drawable.편지봉투);
        //Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        //Canvas canvas = new Canvas(bitmap);
        //drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        //drawable.draw(canvas);
    }

    @Override
    public boolean onPreDraw(){
        boolean emptyText = TextUtils.isEmpty(getText());
        if(mMagnifyingGlassShown != emptyText)
        {
            mMagnifyingGlassShown = emptyText;
            setCompoundDrawables((Drawable)mMagnifyingGlass,null,null,(Drawable)drawable2);
            return false;
        }
        return super.onPreDraw();
    }
}






























