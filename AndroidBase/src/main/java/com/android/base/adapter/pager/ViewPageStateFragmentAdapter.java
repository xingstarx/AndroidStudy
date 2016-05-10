package com.android.base.adapter.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 15:48      <br/>
 * Description：
 */
public class ViewPageStateFragmentAdapter extends FragmentStatePagerAdapter {

    private AdapterDelegate mAdapterDelegate;

    public ViewPageStateFragmentAdapter(FragmentManager fm, AdapterDelegate adapterDelegate) {
        super(fm);
        mAdapterDelegate = adapterDelegate;
        mAdapterDelegate.setPagerAdapter(this);
    }


    public ViewPageStateFragmentAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mAdapterDelegate = AdapterDelegate.create(context);
        mAdapterDelegate.setPagerAdapter(this);
    }



    public void addTab(String title, String tag, Class<?> clazz, Bundle args) {
        mAdapterDelegate.addTab(title, tag, clazz, args);
    }

    public void addAllTab(List<AdapterDelegate.ViewPageInfo> tabs) {
        mAdapterDelegate.addAllTab(tabs);
    }

    private void addFragment(AdapterDelegate.ViewPageInfo info) {
        mAdapterDelegate.addFragment(info);
    }

    /**
     * 移除第一次
     */
    public void remove() {
        remove(0);
    }

    /**
     * 移除一个tab
     *
     * @param index 备注：如果index小于0，则从第一个开始删 如果大于tab的数量值则从最后一个开始删除
     */
    public void remove(int index) {
        mAdapterDelegate.remove(index);
    }

    /**
     * 移除所有的tab
     */
    public void removeAll() {
        mAdapterDelegate.removeAll();
    }

    @Override
    public int getCount() {
        return mAdapterDelegate.getCount();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mAdapterDelegate.getItem(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mAdapterDelegate.getPageTitle(position);
    }

}
