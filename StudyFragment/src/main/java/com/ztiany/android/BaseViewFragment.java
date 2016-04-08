package com.ztiany.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    private boolean mUserSelfAnim;

    private TextView mTextView;

    public BaseViewFragment() {
    }

    @Override
    public void onAttach(Context context) {
        debugLifeCycle();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mTextView == null) {
            mTextView = new TextView(getContext());
            mTextView.setPadding(100, 100, 100, 100);
            mTextView.setBackgroundColor(ResourceUtil.getColor(R.color.colorAccent, getActivity()));
            mTextView.setText(this.getClass().getName());
        }
        Log.d(tag(), "mTextView.getParent():" + mTextView.getParent());
        Log.d(tag(), "onCreateView() called with: " + savedInstanceState + "[ =  savedInstanceState + ] + returnView->" + mTextView);
        return mTextView;
    }

    public void setUserSelfAnim(boolean userSelfAnim) {
        mUserSelfAnim = userSelfAnim;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(tag(), "onCreateAnimation() called with: " + "transit = [" + transit + "], enter = [" + enter + "], nextAnim = [" + nextAnim + "]");
        if (!mUserSelfAnim) {
            return null;
        }

        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {//表示视图显示到界面的行为
            if (enter) {//进入的动作
                return AnimationUtils.loadAnimation(getContext(), R.anim.anim_bottom_in);
            } else {//如果是两个fragment，一个replace另一个，被replace的那个就是false
                return AnimationUtils.loadAnimation(getContext(), R.anim.anim_out);
            }
        } else if (transit == FragmentTransaction.TRANSIT_FRAGMENT_CLOSE) {//表示视图脱离界面的动作
            if (enter) {//之前被replace的重新进入到界面
                return AnimationUtils.loadAnimation(getContext(), R.anim.anim_in);
            } else {//当前Fragment退出
                return AnimationUtils.loadAnimation(getContext(), R.anim.anim_bottom_out);
            }
        }


        return null;
    }


}
