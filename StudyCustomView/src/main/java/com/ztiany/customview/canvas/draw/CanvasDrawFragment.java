package com.ztiany.customview.canvas.draw;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 14:04      <br/>
 * Description：
 */
public class CanvasDrawFragment extends InjectFragment {

    public static CanvasDrawFragment newInstance() {
        return new CanvasDrawFragment();
    }

    @Override
    protected int layoutId() {
        return  R.layout.fragment_canvas_draw;
    }
}
