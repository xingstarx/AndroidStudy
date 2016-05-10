package com.android.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 14:59      <br/>
 * Description：如果需要加入到Stack，建议加上mStackName。
 */
public class FragmentInfo  {

    private int mPagerId;
    private String mTag;
    private Class<? extends Fragment> mClazz;
    private int mTitleId;
    private Bundle mArguments;
    private boolean mIsToStack;
    private String mStackName;

    private Fragment mFragment;

    public Fragment getInstance() {
        return mFragment;
    }

    public void setInstance(Fragment fragment) {
        mFragment = fragment;
    }

    private FragmentInfo(int pagerId, String tag, Class<? extends Fragment> clazz, int titleId, Bundle arguments, boolean toStack, String stackName) {
        mPagerId = pagerId;
        this.mTag = tag;
        mClazz = clazz;
        mTitleId = titleId;
        mArguments = arguments;
        this.mIsToStack = toStack;
        mStackName = stackName;
    }



    public static PageBuilder builder() {
        return new PageBuilder();
    }


    public static class PageBuilder {
        private int mPagerId;
        private String mTag;
        private Class<? extends Fragment> mClazz;
        private int mTitleId;
        private Bundle mArguments;
        private boolean mIsToStack;
        private String mStackName;

        public FragmentInfo build() {
            return new FragmentInfo(mPagerId, mTag, mClazz, mTitleId, mArguments, mIsToStack, mStackName);
        }

        public PageBuilder pagerId(int pagerId) {
            mPagerId = pagerId;
            return this;
        }

        public PageBuilder tag(String tag) {
            this.mTag = tag;
            return this;

        }

        public PageBuilder clazz(Class<? extends Fragment> clazz) {
            mClazz = clazz;
            return this;

        }

        public PageBuilder titleId(int titleId) {
            mTitleId = titleId;
            return this;

        }

        public PageBuilder arguments(Bundle arguments) {
            mArguments = arguments;
            return this;

        }

        public PageBuilder toStack(boolean toStack) {
            this.mIsToStack = toStack;
            return this;

        }

        public PageBuilder stackName(String stackName) {
            mStackName = stackName;
            return this;
        }
    }


    public boolean isToStack() {
        return mIsToStack;
    }

    public String getStackName() {
        return mStackName;
    }

    public Fragment newFragment(Context context) {
        return Fragment.instantiate(context, mClazz.getName(), mArguments);
    }


    public Bundle getArguments() {
        return mArguments;
    }

    public int getTitleId() {
        return mTitleId;
    }

    public int getPagerId() {
        return mPagerId;
    }

    public String getTag() {
        return mTag;
    }

    public Class<? extends Fragment> getClazz() {
        return mClazz;
    }
}
