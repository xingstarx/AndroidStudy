package com.ztiany.customview.customer.ovalmenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.android.base.utils.android.UnitConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-23 18:06      <br/>
 * Description：
 */
public class OvalMenu extends FrameLayout {

    private boolean mIsDebug = true;
    private List<PointF> mDebugPoints;
    private int mScaledTouchSlop;

    {
        if (mIsDebug) {
            mDebugPoints = new ArrayList<>();
            mDebugPoints.clear();
            PointF pointF;
            for (int i = 0; i < 360; i++) {
                pointF = new PointF();
                mDebugPoints.add(pointF);
            }
        }
    }
//Debug/////////////////////////////////////////////////////////////////////////


    private static final int INVALID_POINTER_ID = MotionEvent.INVALID_POINTER_ID;
    private int mActivePointerId;
    private boolean mIsBeginDrag;
    private float mDownX, mDownY, mLastX, mLastY;

    private MenuScroller mMenuScroller;

    private static final int QUADRANT_ONE = 1;
    private static final int QUADRANT_TWO = 2;
    private static final int QUADRANT_THIRD = 3;
    private static final int QUADRANT_FOUR = 4;
    private int mCurrentQuadrant;

    public static final float MAX_FLING_ANGLE = 45;
    public static final float MIN_FLING_ANGLE = 15;

    private long mStartTime;
    private long mEndTime;
    private float mAddAngle;


    private Paint mPaint;
    private static final String TAG = OvalMenu.class.getSimpleName();
    private float mAAxis;//长轴长
    private float mBAxis;//短轴长
    private final static int CIRCLE = 360;
    private OvalMath mOvalMath;

    private int mInitAngle = 90;

    private float mCenterX, mCenterY;

    private float mAAxisPercentByHalfWidth = 0.8F, mBAxisPercentByAAxis = 0.4F;

    private List<ChildProxy> mChildProxyList;


    public OvalMenu(Context context) {
        this(context, null);
    }

    public OvalMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mOvalMath = new OvalMath();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(UnitConverter.dpToPx(2));
        mChildProxyList = new CopyOnWriteArrayList<>();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        mMenuScroller = new MenuScroller();
    }


    public void setDebug(boolean debug) {
        mIsDebug = debug;
        requestLayout();
    }

    public float getAAxisPercentByHalfWidth() {
        return mAAxisPercentByHalfWidth;
    }

    public void setAAxisPercentByHalfWidth(float AAxisPercentByHalfWidth) {
        mAAxisPercentByHalfWidth = AAxisPercentByHalfWidth;
        requestLayout();

    }

    public float getBAxisPercentByAAxis() {
        return mBAxisPercentByAAxis;
    }

    public void setBAxisPercentByAAxis(float BAxisPercentByAAxis) {
        mBAxisPercentByAAxis = BAxisPercentByAAxis;
        requestLayout();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        prepareChildren();
    }


    @Override
    public void addView(View child) {
        super.addView(child);
    }

    private void prepareChildren() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        float angle = CIRCLE / childCount;
        ChildProxy childProxy;
        for (int i = 0; i < childCount; i++) {
            childProxy = new ChildProxy();
            childProxy.child = getChildAt(i);
            childProxy.setAngle(mInitAngle + i * angle);
            mChildProxyList.add(childProxy);
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        //TODO prepareChildren
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        //TODO prepareChildren
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        mAAxis = mCenterX * mAAxisPercentByHalfWidth;
        mBAxis = mAAxis * mBAxisPercentByAAxis;
        mOvalMath.initSize(mAAxis, mBAxis, mCenterX, mCenterY);

        if (mIsDebug && mOvalMath.isChanged) {
            int temp= 0;
            for (PointF debugPoint : mDebugPoints) {
                mOvalMath.calcPoint(temp, debugPoint);
                temp++;
            }
        }
    }

    private void calcChildProxy() {
        for (ChildProxy childProxy : mChildProxyList) {
            childProxy.calcCoordinate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = mChildProxyList.size();
        if (childCount == 0) {
            return;
        }
        calcChildProxy();
        for (ChildProxy childProxy : mChildProxyList) {
            childProxy.child
                    .layout(childProxy.getSelfCenterX(),
                            childProxy.getSelfCenterY(),
                            childProxy.getSelfCenterX() + childProxy.child.getMeasuredWidth(),
                            childProxy.getSelfCenterY() + childProxy.child.getMeasuredHeight());
        }
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mIsDebug && mDebugPoints.size() > 0) {
            for (PointF debugPoint : mDebugPoints) {
                canvas.drawPoint(debugPoint.x, debugPoint.y, mPaint);
            }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        if ((action == MotionEvent.ACTION_MOVE) && (mIsBeginDrag)) {
            return true;
        }


        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mAddAngle = 0;
                mIsBeginDrag = false;
                mStartTime = SystemClock.currentThreadTimeMillis();
                int index = MotionEventCompat.getActionIndex(event);
                mDownX = MotionEventCompat.getX(event, index);
                mDownY = MotionEventCompat.getY(event, index);
                mLastX = mDownX;
                mLastY = mDownY;
                mActivePointerId = MotionEventCompat.getPointerId(event, index);
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (mActivePointerId == INVALID_POINTER_ID) {
                    return false;
                }
                int pointIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                if (pointIndex < 0) {
                    return false;
                }
                int currentX = (int) MotionEventCompat.getX(event, pointIndex);
                int currentY = (int) MotionEventCompat.getY(event, pointIndex);
                float dx = mLastX - currentX;
                float dy = mLastY - currentY;
                if (!mIsBeginDrag) {
                    if (Math.abs(dx) > mScaledTouchSlop || Math.abs(dy) > mScaledTouchSlop) {
                        mIsBeginDrag = true;
                        mLastX = currentX;
                        mLastY = currentY;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                mIsBeginDrag = false;
                break;
            }


        }

        return mIsBeginDrag;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);


        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                int index = event.getActionIndex();
                mActivePointerId = event.getPointerId(index);
                mLastX = (int) MotionEventCompat.getX(event, index);
                mLastY = (int) MotionEventCompat.getY(event, index);
                checkQuadRant(mLastX, mLastY);
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                if (mActivePointerId == MotionEvent.INVALID_POINTER_ID) {
                    return false;
                }
                int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                int currentX = (int) MotionEventCompat.getX(event, pointerIndex);
                int currentY = (int) MotionEventCompat.getY(event, pointerIndex);

                mCurrentQuadrant = checkQuadRant(currentX, currentY);


                float dx = currentX - mLastX;
                float dy = currentY - mLastY;
                if (!mIsBeginDrag && Math.abs(dx) > mScaledTouchSlop || Math.abs(dy) > mScaledTouchSlop) {
                    mIsBeginDrag = true;
                }

                if (mIsBeginDrag) {
                    float start = mOvalMath.getAngle(mLastX, mLastY);
                    float end = mOvalMath.getAngle(currentX, currentY);
                    refreshLayout(start, end);

                    mEndTime = SystemClock.currentThreadTimeMillis();

                    requestLayout();
                    mLastX = currentX;
                    mLastY = currentY;
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                long useTime = mStartTime - mEndTime;
                float speed = mAddAngle * 10 / useTime;
                if (Math.abs(speed) > MIN_FLING_ANGLE) {
                    startFiling(speed > MAX_FLING_ANGLE ? MAX_FLING_ANGLE : speed);
                }
                break;
            }
        }
        return true;

    }

    private void startFiling(float speed) {
        mMenuScroller.startFling(speed);
    }


    private void refreshLayout(float start, float end) {
        float addAngle = 0;
        switch (mCurrentQuadrant) {
            case QUADRANT_ONE: {
                addAngle = (end - start);
                break;
            }
            case QUADRANT_TWO: {
                addAngle = (start - end);
                break;
            }
            case QUADRANT_THIRD: {
                addAngle = (start - end);

                break;
            }
            case QUADRANT_FOUR: {
                addAngle = (end - start);
                break;
            }
        }
        mAddAngle += addAngle;
        upDateChildProxy(addAngle);
    }

    private void upDateChildProxy(float addAngle) {
        for (ChildProxy childProxy : mChildProxyList) {
            childProxy.addAngle(addAngle);
        }

    }

    private int checkQuadRant(float x, float y) {
        int tmpX = (int) (x - mCenterX);
        int tmpY = (int) (y - mCenterY);
        if (tmpX >= 0) {
            return tmpY >= 0 ? QUADRANT_FOUR : QUADRANT_ONE;
        } else {
            return tmpY >= 0 ? QUADRANT_THIRD : QUADRANT_TWO;
        }
    }

    private boolean checkEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean isDebug() {
        return mIsDebug;
    }

    public class MenuScroller implements Runnable {

        private boolean mIsRunning;
        private float mCurrentSpeed;

        boolean isRunning() {
            return mIsRunning;
        }

        void startFling(float speed) {
            if (mIsRunning) {
                removeCallbacks(this);
                mIsRunning = false;
            }
            mCurrentSpeed = speed;
            post(this);
        }


        @Override
        public void run() {
            // 如果小于20,则停止
            if ((int) Math.abs(mCurrentSpeed) < MIN_FLING_ANGLE) {
                mIsRunning = false;
                return;
            }
            mIsRunning = true;
            // 不断改变mStartAngle，让其滚动，/30为了避免滚动太快
            mCurrentSpeed += (mCurrentSpeed / 30);
            // 逐渐减小这个值
            mCurrentSpeed /= 1.0666F;
            upDateChildProxy(-mCurrentSpeed / 8.666F);
            postDelayed(this, 30);
            // 重新布局
            requestLayout();
        }
    }


    public class ChildProxy {
        PointF mPointF = new PointF();
        private float mAngle;
        View child;

        Runnable toFontRunnable = new Runnable() {
            @Override
            public void run() {
                child.bringToFront();
            }
        };

        void setAngle(float angle) {
            mAngle = angle % 360;

            calcCoordinate();
            scaleSelf();
            bringToFrontSelf();
        }

        private void bringToFrontSelf() {
            int quadRant = checkQuadRant(mPointF.x, mPointF.y);
            if (quadRant == QUADRANT_FOUR || quadRant == QUADRANT_THIRD) {
                post(toFontRunnable);
            }
        }

        float getScale() {
            float v = mAngle;
            if (v < 0) {
                v = 360 + v;
            }

            float scale = 0;
            if (v >= 0 && v < 90) {
                scale = 0.75F + ((v / 90F) * 0.25F);
            } else if (v >= 90 && v < 180) {
                v = v - 90;
                scale = 1F - ((v / 90F) * 0.25F);
            } else if (v >= 180 && v < 270) {
                v = v - 180;
                scale = 0.75F - ((v / 90F) * 0.25F);
            } else if (v >= 270 && v < 360) {
                v = v - 270;
                scale = 0.5F + ((v / 90F) * 0.25F);
            }
            return scale;
        }

        int getSelfCenterX() {
            return (int) ((mPointF.x - child.getMeasuredWidth() / 2) + 0.5F);
        }

        int getSelfCenterY() {
            return (int) ((mPointF.y - child.getMeasuredHeight() / 2) + 0.5F);
        }

        private void calcCoordinate() {
            mOvalMath.calcPoint(mAngle, mPointF);
        }


        void scaleSelf() {
            float scale = getScale();
            child.setScaleX(scale);
            child.setScaleY(scale);
        }


        public void addAngle(float addAngle) {
            mAngle = (mAngle + addAngle) % 360;


            calcCoordinate();
            scaleSelf();
            bringToFrontSelf();
        }


    }

}
