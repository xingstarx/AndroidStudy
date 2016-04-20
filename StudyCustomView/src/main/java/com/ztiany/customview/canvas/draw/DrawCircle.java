package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.base.utils.android.UnitConverter;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 14:57      <br/>
 * Description：
 */
public class DrawCircle extends View {

    public DrawCircle(Context context) {
        this(context, null);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(10));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 4, mPaint);
    }


}
