package com.ztiany;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.base.ui.BaseActivity;

import butterknife.ButterKnife;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 10:30      <br/>
 * Description：
 */
public abstract class InjectActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        int layoutId = layoutId();
        setContentView(layoutId);
        ButterKnife.bind(this);
        setupView();
    }


    protected void init(Bundle savedInstanceState) {
    }

    protected abstract void setupView();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
