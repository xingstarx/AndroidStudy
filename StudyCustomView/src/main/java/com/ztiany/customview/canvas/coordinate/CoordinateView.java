package com.ztiany.customview.canvas.coordinate;

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
 * Date 2016-04-20 13:46      <br/>
 * Description：
 */
public class CoordinateView extends View {

    private Paint mPaint;


    public CoordinateView(Context context) {
        this(context, null);
    }

    public CoordinateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(5));
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);

        int trans = UnitConverter.dpToPx(40);
        canvas.translate(trans, trans);

        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);

        canvas.rotate(45);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
    }


}
