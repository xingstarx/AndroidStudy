package com.android.base.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 15:08      <br/>
 * Description：
 */
public abstract class AgentFragment extends BaseFragment {


    /**
     * Init  fragmentDelegate before this fragment attach to Activity,This method will always call by AgentFragment
     *
     * @return FragmentDelegate
     */
    protected abstract FragmentDelegate getDelegate();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getDelegate().onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getDelegate().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDelegate().onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        getDelegate().onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDelegate().onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getDelegate().onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getDelegate().setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getDelegate().onHiddenChanged(hidden);
    }


}

