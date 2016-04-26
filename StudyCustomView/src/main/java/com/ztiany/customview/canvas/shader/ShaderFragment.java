package com.ztiany.customview.canvas.shader;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-26 22:42      <br/>
 * Description：
 */
public class ShaderFragment extends InjectFragment {

    public static ShaderFragment newInstance() {
        return new ShaderFragment();
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_shader;
    }
}
