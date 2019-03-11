package com.qihe.componetdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

public class WTextView extends android.support.v7.widget.AppCompatTextView {
    public WTextView(Context context) {
        this(context, null);
    }

    public WTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private LoadingDrawable mLodingDrawable;

    private void init() {
        mLodingDrawable = new LoadingDrawable(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mLodingDrawable.draw(canvas);
        super.onDraw(canvas);
    }
}
