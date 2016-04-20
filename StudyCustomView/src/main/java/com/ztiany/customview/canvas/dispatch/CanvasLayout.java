package com.ztiany.customview.canvas.dispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 12:47      <br/>
 * Description：
 */
public class CanvasLayout extends FrameLayout {

    private static final String TAG = CanvasLayout.class.getSimpleName();
    public CanvasLayout(Context context) {
        super(context);
    }

    public CanvasLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG, "canvas.getWidth():" + canvas.getWidth());
        Log.d(TAG, "canvas.getHeight():" + canvas.getHeight());
        Log.d(TAG, "getMeasuredHeight():" + getMeasuredHeight());
        Log.d(TAG, "getMeasuredWidth():" + getMeasuredWidth());
        Log.d(TAG, "canvas.getMaximumBitmapHeight():" + canvas.getMaximumBitmapHeight());
        Log.d(TAG, "canvas.getMaximumBitmapWidth():" + canvas.getMaximumBitmapWidth());

    }
}
