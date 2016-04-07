package com.ztiany.library.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.android.base.ui.Presenter;
import com.android.base.ui.UI;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 19:56      <br/>
 * Description：
 */
public abstract class BasePresenter<V extends UI> implements Presenter<V> {

    private V mRootView;

    @Override
    @CallSuper
    public void onCreate(V view, Bundle saveInstance) {
        mRootView = view;
        if (mRootView == null) {
            throw new NullPointerException(" BasePresenter onCreate -->view is null");
        }
    }

    @Override
    public abstract void onCreated();

    @Override
    public void onResume() {

    }

    @Override
    public void onSaveInstance(Bundle saveInstance) {

    }

    @Override
    public void onPause() {

    }

    protected V getView() {
        return mRootView;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        mRootView = null;
    }
}
