package com.ztiany.customview.scroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.customview.R;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-18 16:44                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class OverScrollFragment extends Fragment {

    public static OverScrollFragment newInstance() {
        return new OverScrollFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_over_scroller, container, false);
    }


}
