package com.ztiany.android.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.base.ui.BaseFragment;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-07 16:36                                                       <br/>
 * description                                                                             <br/>
 * vsersion
 */
public class BaseDrawerFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setPadding(100, 100, 100, 100);
        textView.setText(this.getClass().getName());
        return textView;
    }
}
