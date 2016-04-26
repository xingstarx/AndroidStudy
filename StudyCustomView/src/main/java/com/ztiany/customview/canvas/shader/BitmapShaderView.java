package com.ztiany.customview.canvas.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-26 22:37      <br/>
 * Description：
 */
public class BitmapShaderView extends View {

    private static final String TAG = BitmapShaderView.class.getSimpleName();

    Bitmap mBitmap;
    private Paint mPaint;
    private Rect mRect;
    private Matrix mMatrix;
    private BitmapShader mShader;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shader);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(mShader);

        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new Rect(0, 0, w, h);
//        mMatrix.setTranslate(30, h / 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "mMatrix:" + mMatrix);

//        mMatrix.postSkew(0.2F, 0.30F);
        Log.d(TAG, "mMatrix:" + mMatrix);

        mMatrix.setTranslate(-getWidth()/2, getHeight()/2);
//        mMatrix.postScale(-1 , 1);

//        mShader.setLocalMatrix(mMatrix);
//        canvas.drawRect(mRect, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}
