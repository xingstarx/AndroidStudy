package com.ztiany.customview.canvas.matrix;

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


    public static MatrixFragment newInstance() {
        return new MatrixFragment();
    }
    @Override
    protected int layoutId() {
        return R.layout.fragment_matrix;
    }
}
