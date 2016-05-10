package com.android.base.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-04 15:40      <br/>
 * Description：
 */
public abstract class BaseActivity extends AbsActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        int layoutId = layoutId();
        setContentView(layoutId);
        ButterKnife.bind(this);
        setupView(savedInstanceState);
    }

    /**
     * Before setContent
     *
     * @param savedInstanceState state
     */
    protected void init(Bundle savedInstanceState) {
    }


    protected abstract int layoutId();


    /**
     * after setContentView
     */
    protected abstract void setupView(@Nullable Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
