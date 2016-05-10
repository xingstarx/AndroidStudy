package com.ztiany.android4.features;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.SurfaceTexture;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.TextureView;


/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-10 19:51      <br/>
 * Description：
 */
public class LoadingTextureView extends TextureView implements TextureView.SurfaceTextureListener {


    private float mMinWidth = 30;//dp

    private float mFinalSize;

    private float mCurrentLeftCenterX;
    private float mMinLeftCenterX;
    private float mMaxLeftCenterX;
    private float mCenterY;
    private float mCenterX;
    private float mRightCenterX;

    private volatile boolean isCompleted;
    private volatile boolean isDrawing;



    private final Paint mCleanPaint;


    private Paint mPaint;

    private   float mIncrement;//除


    private final static int SLEEP_TIME = 50;


    public LoadingTextureView(Context context) {
        this(context, null);
    }

    public LoadingTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCleanPaint = new Paint();
        //清屏
        mCleanPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        mCleanPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        init();
    }

    private void init() {
        mMinWidth = UnitConverter.dpToPx(mMinWidth);
        setSurfaceTextureListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode != MeasureSpec.EXACTLY) {
            width = (int) mMinWidth;
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        } else {
            width = widthSize;
        }

        if (heightMode != MeasureSpec.EXACTLY) {
            height = width / 2;
        } else {
            height = heightSize;
        }

        setMeasuredDimension(width, height);

    }


    @Override
    protected void onSizeChanged(int mWidth, int mHeight, int oldw, int oldh) {
        super.onSizeChanged(mWidth, mHeight, oldw, oldh);

        mFinalSize = mWidth / 4;
        mCurrentLeftCenterX = mWidth / 2 - mWidth / 8;
        mMinLeftCenterX = mWidth / 4;
        mMaxLeftCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mCenterX = mWidth / 2;

        mIncrement = mFinalSize / 30;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        isCompleted = false;
        if (!isDrawing) {
            new DrawThread().start();
            isDrawing = true;
        }
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        isCompleted = true;
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    private class DrawThread extends Thread {

        private boolean mIsAdd;

        @Override
        public void run() {
            int rotate = 1;
            while (!isCompleted) {
                Canvas canvas = null;
                try {
                    canvas = lockCanvas();
                    long startTime = SystemClock.currentThreadTimeMillis();
                    calcRadius();
                    drawCircle(canvas);
                    setRotation(rotate++);
                    long interval = SystemClock.currentThreadTimeMillis() - startTime;
                    if (interval < SLEEP_TIME) {
                        SystemClock.sleep(SLEEP_TIME - interval);
                    }
                } finally {
                    unlockCanvasAndPost(canvas);
                }
            }
            isDrawing = false;
        }

        private void calcRadius() {
            if (mIsAdd) {
                mCurrentLeftCenterX += mIncrement;
            } else {
                mCurrentLeftCenterX -= mIncrement;
            }

            if (mCurrentLeftCenterX <= mMinLeftCenterX) {
                mCurrentLeftCenterX = mMinLeftCenterX;
                mIsAdd = true;
            }

            if (mCurrentLeftCenterX >= mMaxLeftCenterX) {
                mCurrentLeftCenterX = mMaxLeftCenterX;
                mIsAdd = false;
            }

            mRightCenterX = mFinalSize + mCurrentLeftCenterX;
        }

        private void drawCircle(Canvas canvas) {
            canvas.drawPaint(mCleanPaint);
            canvas.drawColor(Color.WHITE);
            canvas.drawCircle(mCurrentLeftCenterX, mCenterY, mCenterX - mCurrentLeftCenterX, mPaint);
            canvas.drawCircle(mRightCenterX, mCenterY, mRightCenterX - mCenterX, mPaint);
        }
    }


}
