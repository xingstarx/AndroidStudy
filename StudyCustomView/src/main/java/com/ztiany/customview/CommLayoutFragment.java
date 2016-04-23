package com.ztiany.customview;

import android.content.Context;
import android.os.Bundle;

import com.ztiany.InjectFragment;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-23 11:40      <br/>
 * Description：
 */
public class CommLayoutFragment extends InjectFragment {

    public static final String LAYOUT_ID = "layout_id";
    private int mLayoutId;


    public static CommLayoutFragment newInstance(int layoutId) {
        CommLayoutFragment commLayoutFragment = new CommLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_ID, layoutId);
        commLayoutFragment.setArguments(bundle);
        return commLayoutFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mLayoutId = arguments.getInt(LAYOUT_ID);
        }


    }

    @Override
    protected int layoutId() {
        return mLayoutId;
    }
}
