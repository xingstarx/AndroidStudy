package com.ztiany.customview.canvas.dispatch;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 12:49      <br/>
 * Description：
 */
public class DispatchFragment extends InjectFragment {

    public static DispatchFragment newInstance() {
        return new DispatchFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_canvas_dispatch;
    }
}
