package com.ztiany.customview.canvas.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-30 23:54      <br/>
 * Description：
 */
public class ClipCanvasView extends View {

    private Bitmap mBitmap;
    private Path mPath;
    private Rect mRect;

    public ClipCanvasView(Context context) {
        this(context, null);
    }

    public ClipCanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shader);

        mPath = new Path();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        canvas.translate(measuredWidth / 2, measuredHeight / 2);
        mPath.addCircle(0, 0, measuredHeight / 4, Path.Direction.CW);
        canvas.clipPath(mPath);
      /*  mRect.set(-width / 2, 0, width / 2, height / 2);
        canvas.clipRect(mRect, Region.Op.UNION);*/
        canvas.drawBitmap(mBitmap, -width / 2, -height / 2, null);
    }
}
