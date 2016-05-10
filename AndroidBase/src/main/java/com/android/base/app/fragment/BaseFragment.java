package com.android.base.app.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.base.app.AbsFragment;

import butterknife.ButterKnife;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-04 11:29      <br/>
 * Description：
 */
public abstract class BaseFragment extends AbsFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
