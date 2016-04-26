package com.ztiany.customview.canvas.rect_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 16:19                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class RectView extends View {


    private RectF mRect;

    private Paint mPaint;

    private float mWidth, mHeight;

    public RectView(Context context) {
        this(context, null);
    }

    public RectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mRect = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        mRect.set(-mWidth / 4F, -mHeight / 4F, mWidth / 4F, mHeight / 4F);

        canvas.drawRect(mRect, mPaint);


        mRect.inset(mWidth / 8, -mWidth / 8);

        mPaint.setColor(Color.RED);
        canvas.drawRect(mRect, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawOval(mRect, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(mRect, 0, 270, true, mPaint);


    }
}
