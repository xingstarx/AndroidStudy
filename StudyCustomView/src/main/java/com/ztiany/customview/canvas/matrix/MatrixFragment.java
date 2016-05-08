package com.ztiany.customview.canvas.matrix;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-27 16:01                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class MatrixFragment  extends InjectFragment{

    private ViewGroup mViewGroup;
    private static final String TAG = MatrixFragment.class.getSimpleName();


    public static MatrixFragment newInstance() {
        return new MatrixFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(Menu.NONE, 1, 1, "matrixImageView").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mViewGroup.removeAllViews();
                MatrixView child = new MatrixView(getContext());
                child.setImageResource(R.drawable.shader);
                mViewGroup.addView(child);

                return true;
            }
        });
        menu.add(Menu.NONE, 2, 2, "rotate").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick() called with: " + "item = [" + item + "]");
                mViewGroup.removeAllViews();
                mViewGroup.addView(new RotateMatrixDemoView(getContext()));
                return true;
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_matrix;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewGroup = (ViewGroup) view;
    }
}
