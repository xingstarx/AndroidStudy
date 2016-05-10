package com.android.base.adapter.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 15:37      <br/>
 * Description：
 */
public class AdapterDelegate {


    private final Context mContext;
    private final ArrayList<ViewPageInfo> mTabs = new ArrayList<>();

    PagerAdapter mPagerAdapter;

    public PagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }


    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }

    public AdapterDelegate(Context context) {
        mContext = context;
    }

    public static AdapterDelegate create(Context context) {

        return new AdapterDelegate(context);
    }


    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }


    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clazz.getName(), info.args);
    }


    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }


    public void addTab(String title, String tag, Class<?> clazz, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clazz, args);
        addFragment(viewPageInfo);
    }

    public void addAllTab(List<ViewPageInfo> mTabs) {
        for (ViewPageInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }

    public void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }
        mTabs.add(info);
        mPagerAdapter.notifyDataSetChanged();
    }


    public void remove(int index) {
        if (mTabs.isEmpty()) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= mTabs.size()) {
            index = mTabs.size() - 1;
        }
        mTabs.remove(index);
        mPagerAdapter.notifyDataSetChanged();
    }


    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mTabs.clear();
        mPagerAdapter.notifyDataSetChanged();
    }


    public int getCount() {
        return mTabs.size();
    }

    public static class ViewPageInfo {
        public final String tag;
        public final Class<?> clazz;
        public final Bundle args;
        public final String title;

        public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
            title = _title;
            tag = _tag;
            clazz = _class;
            args = _args;
        }
    }

}
