package com.ztiany;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.app.fragment.BaseFragment;

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
        return inflater.inflate(layoutId(), container, false);
    }

    protected abstract int layoutId();



}
