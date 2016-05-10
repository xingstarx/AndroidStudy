package com.ztiany.customview.canvas.coordinate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.app.fragment.BaseFragment;


/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 13:50      <br/>
 * Description：
 */
public class CoordinateFragment extends BaseFragment {

    public static CoordinateFragment newInstance() {
        return new CoordinateFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CoordinateView coordinateView = new CoordinateView(getContext());
        return coordinateView;
    }
}
