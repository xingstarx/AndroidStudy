package com.ztiany.android.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.app.fragment.BaseFragment;


/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-09 23:57      <br/>
 * Description：
 */
public class ContentFragment extends BaseFragment {

    static ContentFragment newInstance() {
        return new ContentFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppCompatTextView textView = new AppCompatTextView(getContext());
        textView.setText("showBottomSheetDialogInFragment");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragmentDialog bottomSheetFragmentDialog = BottomSheetFragmentDialog.newInstance();

                bottomSheetFragmentDialog.setTargetFragment(ContentFragment.this, 100);

                bottomSheetFragmentDialog.show(getChildFragmentManager(), "BottomSheetDialogFragmentNest");
            }
        });
        return textView;
    }
}
