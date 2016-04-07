package com.android.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-18 15:26
 *         description
 *         vsersion
 */
public class BaseFragment extends Fragment {

    private boolean printLifeCycle = false;

    protected BaseFragment debugLifeCycle() {
        printLifeCycle = true;
        return this;
    }

    protected String tag() {
        return this.getClass().getCanonicalName();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onAttach");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onCreate");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onCreateView");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onViewCreated");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onStart");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onResume");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onPause");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onStop");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onDestroyView");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onDestroy");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onDetach");
        }
    }


    /**
     * 此方法会在与ViewPager使用时被调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->setUserVisibleHint ==" + isVisibleToUser);
        }
    }

    /**
     * 当hide或者show一个fragment时 方法可能会被调用
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (printLifeCycle) {
            Log.d(tag(), getClass().getSimpleName() + "-->onHiddenChanged = " + hidden);
        }
    }


    protected <T extends View> T findView(@IdRes int viewId) {
        if (getView() != null) {
            @SuppressWarnings("unchecked")//需要什么类型，就返回什么类型
                    T view = (T) getView().findViewById(viewId);
            return view;

        }
        return null;
    }
}
