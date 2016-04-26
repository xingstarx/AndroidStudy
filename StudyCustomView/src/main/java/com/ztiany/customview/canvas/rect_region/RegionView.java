package com.ztiany.customview.canvas.rect_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 18:05                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class RegionView extends View {


    private Region mRegionA;
    private Region mRegionB;
    private Paint mPaint;


    public RegionView(Context context) {
        this(context, null);
    }

    public RegionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRegionA = new Region();
        mRegionB = new Region();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);


        mRegionA.set(100, 100, 300, 300);

        mRegionB.set(200, 200, 400, 400);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // 填充颜色
        canvas.drawColor(Color.BLUE);

        canvas.save();

        // 裁剪区域A
        canvas.clipRegion(mRegionA);

        // 再通过组合方式裁剪区域B
        canvas.clipRegion(mRegionB, Region.Op.UNION);

        // 填充颜色
        canvas.drawColor(Color.RED);

        canvas.restore();

        // 绘制框框帮助我们观察
        canvas.drawRect(100, 100, 300, 300, mPaint);
        canvas.drawRect(200, 200, 400, 400, mPaint);

    }
}
