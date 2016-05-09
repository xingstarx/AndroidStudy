package com.ztiany.customview.scroll.overscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.OverScroller;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-18 11:47                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class ScrollerView extends FrameLayout {


    private static final String TAG = ScrollerView.class.getSimpleName();


    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;
    private int mScaledTouchSlop;
    private boolean isBeginDrag;


    private boolean mBackOnUp = false;


    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mScaledMinimumFlingVelocity;


    public ScrollerView(Context context) {
        this(context, null);
    }

    public ScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScaledMinimumFlingVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        mVelocityTracker = VelocityTracker.obtain();
        mScroller = new OverScroller(getContext());
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();


        int actionMask = ev.getAction();


        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: {
                isBeginDrag = false;
                mDownX = x;
                mDownY = y;
                mLastX = mDownX;
                mLastY = mDownY;
                return isBeginDrag;
            }
            case MotionEvent.ACTION_MOVE: {

                if (!isBeginDrag) {
                    int dx = mLastX - mDownX;
                    int dy = mLastY - mDownY;
                    if (Math.abs(dx) > mScaledTouchSlop || Math.abs(dy) > mScaledTouchSlop) {
                        isBeginDrag = true;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                isBeginDrag = false;
                break;
            }
        }

        mLastX = x;
        mLastY = y;

        return isBeginDrag;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        mVelocityTracker.addMovement(ev);
        int actionMask = ev.getAction();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int dx = mLastX - x;
                int dy = mLastY - y;
                scrollBy(0, dy);
                mLastX = x;
                mLastY = y;
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                isBeginDrag = false;
                if (mBackOnUp) {
                    scrollBack();
                } else {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float yVelocity = mVelocityTracker.getYVelocity();
                    mVelocityTracker.clear();
                    if (Math.abs(yVelocity) > mScaledMinimumFlingVelocity) {
                        fling(-yVelocity);
                    }
                }
                break;

            }
        }
        return true;
    }

    private void fling(float v) {
        Log.d(TAG, "fling() called with: " + "v = [" + v + "]");
        Log.d(TAG, "getScrollX():" + getScrollX());
        Log.d(TAG, "getScrollY():" + getScrollY());
        Log.d(TAG, "v:" + v);
        mScroller.fling(
                getScrollX(),
                getScrollY(),
                0, (int) v,
                0, 0,
                0, 1500 , 0,300);
        invalidate();
    }

    private void scrollBack() {
        mScroller.startScroll(
                0,
                getScrollY(),
                0,
                0 - getScrollY(),
                300
        );
        invalidate();
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            Log.d(TAG, "currY:" + currY);
            scrollTo(0, currY);
            invalidate();
        }
    }


}
