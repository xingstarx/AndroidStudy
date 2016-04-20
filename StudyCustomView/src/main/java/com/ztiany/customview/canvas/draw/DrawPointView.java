package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.android.base.utils.android.UnitConverter;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-21 0:03      <br/>
 * Description：
 */
public class DrawPointView extends View {

    public DrawPointView(Context context) {
        this(context, null);
    }

    public DrawPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private Path mPath;

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(20));

        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(getWidth()/3,getHeight()/2,mPaint);

        mPaint.setColor(Color.BLUE);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(getWidth()/4,getHeight()/2,mPaint);

        mPaint.setColor(Color.YELLOW);

        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(getWidth()/5,getHeight()/2,mPaint);

        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(12));

        mPath.moveTo(getWidth()/2, getHeight()/2);
        mPath.addArc(  getWidth()/4, getHeight() / 4,  (getWidth()/1.5F),getHeight() / 1.5F, 0, 180);

        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPath.close();
        canvas.drawPath(mPath,mPaint);

    }
}
