package com.android.base.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.android.base.adapter.pager.LoopPagerAdapter;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 14:34      <br/>
 * Description：
 */
public class LoopViewPager extends ViewPager {

    private AutoScroller mAutoScroller;
    public static final int DEFAULT_INTERVAL = 3000;
    private int mInterval;
    private LoopPagerAdapter mLoopPagerAdapter;

    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInterval(int interval) {
        mInterval = interval;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (LoopPagerAdapter.class.isInstance(adapter)) {
            throw new IllegalArgumentException("LoopViewPager need LoopPagerAdapter");
        }
        super.setAdapter(adapter);
        mLoopPagerAdapter = (LoopPagerAdapter) adapter;
        if (mAutoScroller == null) {
            mAutoScroller = new AutoScroller();
        }
    }


    public void setPageToMiddle() {
        int count = mLoopPagerAdapter.getCount();
        if (count < 2) {
            return;
        }
        int currentPager = getAdapter().getCount() / 2;
        currentPager = currentPager - (currentPager % mLoopPagerAdapter.getRealCount());
        setCurrentItem(currentPager, false); // 设置当前选中的页面
    }


    public void stop() {
        if (mAutoScroller != null) {
            mAutoScroller.stop();
        }
    }

    public void start() {
        if (mAutoScroller != null) {
            mAutoScroller.start();
        }
    }

    private class AutoScroller implements Runnable {

        private boolean mIsLooping;

        private void stop() {
            removeCallbacks(this);
            mIsLooping = false;
        }

        private void start() {
            if (mIsLooping) {
                return;
            }
            postDelayed(this, mInterval);
            mIsLooping = true;
        }

        @Override
        public void run() {
            if (mIsLooping) {
                setCurrentItem(getCurrentItem() + 1);
                postDelayed(this, mInterval);
            }
        }


    }


}
