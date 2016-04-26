package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.android.base.utils.android.UnitConverter;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 14:09      <br/>
 * Description：
 */
public class DrawTextView extends View {


    private Paint mPaint;
    private int mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private Path mPath;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextSize = UnitConverter.spToPx(20);
        mPaint.setTextSize(mTextSize);
        mFontMetrics = new Paint.FontMetrics();
        mPath = new Path();
    }

    private String mText = "Canvas Draw Text yes  画布绘制文字";

    private String mPathText = "Canvas Draw Text yes  onPath , Canvas Draw Text yes  onPath , Canvas Draw Text yes  onPath , Canvas Draw Text yes  onPath";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTypeface(Typeface.SERIF);
        mPaint.setTextSize(UnitConverter.spToPx(18));

        canvas.drawARGB(0xFF, 0xFF, 0xFF, 0xFF);
        int halfHeight = getMeasuredHeight() / 2;
        canvas.drawLine(0, halfHeight, getMeasuredWidth(), halfHeight, mPaint);
        mPaint.getFontMetrics(mFontMetrics);
        mPaint.setTextSize(UnitConverter.spToPx(10));

        canvas.drawText(mText, 0, (halfHeight - ((mFontMetrics.ascent + mFontMetrics.descent) / 2)), mPaint);

        //Direction 表示设置在使用path绘制文本时的位置
        // Path.Direction.CCW表示在path的外围
        //  Path.Direction.CW表示在path的内围

        mPath.addCircle((float) (getMeasuredWidth() / 1.5), getMeasuredHeight() / 2, getMeasuredHeight() / 4, Path.Direction.CCW);




        canvas.drawTextOnPath(mPathText, mPath, 0, 0, mPaint);
    }
}
