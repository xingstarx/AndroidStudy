package com.ztiany.customview.canvas.pathmeasure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-09 21:45      <br/>
 * Description：
 */
public class PathMeasureFragment extends Fragment {


    private PathMeasureAnimView mChild;

    public static PathMeasureFragment newInstance() {
        return new PathMeasureFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button button = new AppCompatButton(getContext());
        button.setText("start");
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChild.startAnim();
            }
        });
        linearLayout.addView(button);
        mChild = new PathMeasureAnimView(getContext());
        linearLayout.addView(mChild);
        return linearLayout;
    }

}
