package com.ztiany.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.base.ui.BaseFragment;
import com.android.base.utils.android.ResourceUtil;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-07 16:36                                                       <br/>
 * description                                                                             <br/>
 * vsersion
 */
public class BaseViewFragment extends BaseFragment {

    public BaseViewFragment() {
    }

    @Override
    public void onAttach(Context context) {
        debugLifeCycle();
        super.onAttach(context);
    }

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mTextView == null) {
            mTextView = new TextView(getContext());
            mTextView.setPadding(100, 100, 100, 100);
            mTextView.setBackgroundColor(ResourceUtil.getColor(R.color.colorAccent, getActivity()));
            mTextView.setText(this.getClass().getName());
        }
        Log.d(tag(), "onCreateView() called with: " + savedInstanceState + "[ =  savedInstanceState + ] + returnView->" + mTextView);
        return mTextView;
    }
}
