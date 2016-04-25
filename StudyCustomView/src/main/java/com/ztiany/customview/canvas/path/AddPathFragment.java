package com.ztiany.customview.canvas.path;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-25 22:16      <br/>
 * Description：
 */
public class AddPathFragment extends InjectFragment {


    public static AddPathFragment newInstance() {
        return new AddPathFragment();
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_add_path;
    }

}
