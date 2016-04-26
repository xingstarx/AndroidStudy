package com.ztiany.customview.canvas.rect_region;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-26 16:30                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class RectWithRegionFragment extends InjectFragment {

    public static RectWithRegionFragment newInstance() {
        return new RectWithRegionFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_rect_region;
    }
}
