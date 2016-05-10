package com.ztiany;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.app.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 10:30      <br/>
 * Description：
 */
public abstract class InjectFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = layoutId();
        View view = inflater.inflate(layoutId, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int layoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
