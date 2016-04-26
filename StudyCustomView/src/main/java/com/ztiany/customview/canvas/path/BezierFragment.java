package com.ztiany.customview.canvas.path;

import android.support.v4.app.Fragment;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 11:37                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class BezierFragment extends InjectFragment {
    @Override
    protected int layoutId() {
        return R.layout.fragment_bezier;
    }

    public static Fragment newInstance() {
        return new BezierFragment();
    }
}
