package com.android.base.adapter.pager;

import com.android.base.utils.object.Checker;

import java.util.List;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-06 14:27      <br/>
 * Description：
 */
public abstract class LoopPagerAdapter<T> extends ViewPagerAdapter<T> {


    public LoopPagerAdapter(List<T> data) {
        super(data);
    }


    public int getRealPosition(int position) {
        if (getCount() == 1) {
            return 0;
        }
        return position % mData.size();
    }

    @Override
    public int getCount() {
        return Checker.isEmpty(mData) ? 0 : mData.size() == 1 ? 1 : Integer.MAX_VALUE;
    }


    public int getRealCount() {
        return super.getCount();
    }


}
