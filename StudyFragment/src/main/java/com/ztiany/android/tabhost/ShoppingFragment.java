package com.ztiany.android.tabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.app.fragment.BaseFragment;


/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-31 16:01
 *         description
 *         vsersion
 */
public class ShoppingFragment extends BaseFragment {
    private static final String TAG = ShoppingFragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        debugLifeCycle();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        Log.d(TAG, "onCreateView() called with: " + "ShoppingFragment");
        appCompatTextView.setText("ShoppingFragment");


        return appCompatTextView;
    }

}
