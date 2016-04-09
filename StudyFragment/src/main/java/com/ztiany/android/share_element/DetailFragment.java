package com.ztiany.android.share_element;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.base.ui.BaseFragment;
import com.android.base.utils.android.ResourceUtil;
import com.ztiany.android.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-09 11:35      <br/>
 * Description：
 */
public class DetailFragment extends BaseFragment {


    private ImageView mImageView;
    private final static String DRAWABLE_KEY = "drawble_key";
    final static String TRANS_NAME = "placekitten";

    static DetailFragment newInstance(int drawableId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE_KEY, drawableId);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deatil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = findView(R.id.frag_detai_iv);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int anInt = arguments.getInt(DRAWABLE_KEY);
            Drawable drawable = ResourceUtil.getDrawable(anInt, getActivity());
            if (drawable != null) {
                mImageView.setImageDrawable(drawable);
            }
        }
    }
}
