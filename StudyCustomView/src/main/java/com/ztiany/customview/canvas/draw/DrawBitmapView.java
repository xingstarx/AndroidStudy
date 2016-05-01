package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-30 20:22      <br/>
 * Description：
 */
public class DrawBitmapView extends View {

    Bitmap mBitmap;

    Rect mSrcRect;

    Paint mPaint;
    RectF mDstRect;
    private PorterDuffXfermode mXfermode;

    private Matrix mMatrix;

    public DrawBitmapView(Context context) {
        this(context, null);
    }

    public DrawBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shader);
        mSrcRect = new Rect();
        mDstRect = new RectF();
        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        mMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mBitmap.getHeight() * 4, MeasureSpec.EXACTLY));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        mSrcRect.set(0, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        mDstRect.set(mSrcRect);
        mDstRect.top += mBitmap.getHeight();
        mDstRect.bottom += mBitmap.getHeight();

        canvas.drawBitmap(mBitmap, mSrcRect, mDstRect, null);


        mDstRect.top += mBitmap.getHeight();
        mDstRect.bottom = mDstRect.top + (mBitmap.getHeight() / 2);
        mDstRect.right = mBitmap.getWidth() / 2;


        canvas.drawBitmap(mBitmap, null, mDstRect, null);

        mMatrix.setScale(0.5F,0.5F);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}
