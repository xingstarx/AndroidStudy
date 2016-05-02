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
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-25 22:16      <br/>
 * Description：
 */
public class AddPathFragment extends Fragment {


    public static AddPathFragment newInstance() {
        return new AddPathFragment();
    }


    private FrameLayout mFrameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem menuItem = menu.add("AddPath");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new AddPathView(getContext()));
                return true;
            }
        });

        MenuItem waterItem = menu.add("PathEffective");
        waterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(new PathEffectView(getContext()));
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFrameLayout == null) {
            mFrameLayout = new FrameLayout(getContext());
            mFrameLayout.addView(new PathEffectView(getContext()));
        }
        return mFrameLayout;
    }

}
