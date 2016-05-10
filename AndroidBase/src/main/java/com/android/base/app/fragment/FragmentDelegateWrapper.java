package com.android.base.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 22:45      <br/>
 * Description：
 */
public class FragmentDelegateWrapper extends FragmentDelegate {


    private FragmentDelegate mWrapped;

    public FragmentDelegateWrapper( FragmentDelegate fragmentDelegate) {
        if (mWrapped != null) {
            mWrapped = fragmentDelegate;
        }
    }

    @CallSuper
    protected void onAttach(Context context) {
        if (mWrapped != null) {
            mWrapped.onAttach(context);
        }

    }

    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (mWrapped != null) {
            mWrapped.onCreate(savedInstanceState);
        }
    }

    @CallSuper
    protected void onViewCreated(@Nullable View view, Bundle savedInstanceState) {
        if (mWrapped != null) {
            mWrapped.onViewCreated(view, savedInstanceState);
        }
    }

    @CallSuper
    protected void onStart() {
        if (mWrapped != null) {
            mWrapped.onStart();
        }
    }

    @CallSuper

    protected void onResume() {
        if (mWrapped != null) {
            mWrapped.onResume();
        }
    }

    @CallSuper
    protected void onPause() {
        if (mWrapped != null) {

            mWrapped.onPause();
        }
    }

    @CallSuper
    protected void onStop() {
        if (mWrapped != null) {
            mWrapped.onStop();
        }
    }

    @CallSuper
    protected void onDestroy() {
        if (mWrapped != null) {
            mWrapped.onDestroy();
        }
    }

    @CallSuper
    protected void onDestroyView() {
        if (mWrapped != null) {
            mWrapped.onDestroyView();
        }

    }

    @CallSuper
    protected void onDetach() {
        if (mWrapped != null) {

            mWrapped.onDetach();
        }
    }

    @CallSuper
    protected void setUserVisibleHint(boolean isVisibleToUser) {
        if (mWrapped != null) {

            mWrapped.setUserVisibleHint(isVisibleToUser);
        }

    }

    @CallSuper
    protected void onHiddenChanged(boolean hidden) {
        if (mWrapped != null) {
            mWrapped.onHiddenChanged(hidden);
        }
    }

}
