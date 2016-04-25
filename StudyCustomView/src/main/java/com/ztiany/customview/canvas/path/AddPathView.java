package com.ztiany.customview.canvas.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.android.base.utils.android.UnitConverter;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-25 22:04      <br/>
 * Description：
 */
public class AddPathView extends View {

    private Path mPath;
    private Paint mPaint;
    private Path mPathByAdd;
    private RectF mRectF;

    String mText = "A B C D E F G H I J K L M N O P Q R  S T U V W X Y Z";

    public AddPathView(Context context) {
        this(context, null);
    }

    public AddPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(UnitConverter.spToPx(18));

        mPathByAdd = new Path();
        mRectF = new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPath.addRect(-300, -300, 300, 300, Path.Direction.CW);
//        mPathByAdd.addCircle(0, 0, UnitConverter.dpToPx(20), Path.Direction.CW);
//        mPath.setLastPoint(200, -200);
//        mPath.addPath(mPathByAdd, 0,- 300);

            canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
            mRectF.set(0, 0, 300, 300);
//            mPath.lineTo(100,-100);
            mPath.addArc(mRectF, 0, 270);
//            mPath.arcTo(mRectF,0,270,false);
            mPath.lineTo(500,500);
            mPath.rLineTo(100,-100);

        canvas.drawPath(mPath, mPaint);
        canvas.drawTextOnPath(mText, mPath, 0, 0, mPaint);
    }
}
