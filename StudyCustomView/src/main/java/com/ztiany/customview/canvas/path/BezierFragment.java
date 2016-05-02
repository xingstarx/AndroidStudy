package com.ztiany.customview.canvas.path;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 11:37                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class BezierFragment extends Fragment {

    private FrameLayout mFrameLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static Fragment newInstance() {
        return new BezierFragment();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem menuItem = menu.add("贝塞尔曲线");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new BezierView(getContext()));
                return true;
            }
        });

        MenuItem waterItem = menu.add("模拟水波纹");
        waterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new WaterView(getContext()));
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFrameLayout == null) {
            mFrameLayout = new FrameLayout(getContext());
            mFrameLayout.addView(new BezierView(getContext()));
        }
        return mFrameLayout;
    }
}
