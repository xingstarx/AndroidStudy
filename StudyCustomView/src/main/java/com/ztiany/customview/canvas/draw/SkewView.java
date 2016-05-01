package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-30 22:09      <br/>
 * Description：
 */
public class SkewView extends View {

    private Paint mPaint;

    public SkewView(Context context) {
        this(context,null);
    }

    public SkewView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SkewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        // x 方向上倾斜45 度
        canvas.skew(1F, 0);
        mPaint.setColor(0x88FF0000);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
    }
}
