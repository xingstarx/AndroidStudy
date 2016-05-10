package com.android.base.app;

import android.support.annotation.CallSuper;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 19:56      <br/>
 * Description：Presenter基础实现
 */
public abstract class AbsPresenter<V extends BaseView> implements IPresenter {

    private V mRootView;
    private boolean mIsResume;

    public AbsPresenter(V rootView) {
        mRootView = rootView;
        if (mRootView == null) {
            throw new NullPointerException(" AbsPresenter onCreate -->view is null");
        }
    }

    @SuppressWarnings("unchecked")
    public void bindView() {
        mRootView.setPresenter(this);
    }


    @Override
    @CallSuper
    public void onStart() {
        mIsResume = true;
    }


    @Override
    @CallSuper
    public void onPause() {
        mIsResume = false;
    }

    protected V getView() {
        return mRootView;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        mRootView = null;
    }


    protected boolean isDestory() {
        return mRootView != null;
    }

    protected boolean isResume() {
        return mIsResume;
    }
}
