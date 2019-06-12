package com.worldtechq.mywallapp.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import android.support.annotation.Nullable;

public class SqaureImage extends ImageView {
    public SqaureImage(Context context) {
        super (context);
    }

    public SqaureImage(Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
    }

    public SqaureImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
    }

    public SqaureImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super (context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure (widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension (getMeasuredWidth (),getMeasuredWidth ());
    }
}
