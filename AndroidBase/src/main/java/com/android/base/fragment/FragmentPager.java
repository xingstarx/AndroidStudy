package com.android.base.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 15:20      <br/>
 * Description：
 */
public abstract class FragmentPager {

    private List<FragmentInfo> mPages;

    public FragmentPager() {
        mPages = new ArrayList<>();
    }

    protected void add(FragmentInfo page) {
        mPages.add(page);
    }


    public FragmentInfo homePage() {
        return mPages.get(0);
    }

    public FragmentInfo formPageId(int id) {
        for (FragmentInfo page : mPages) {
            if (page.getPagerId() == id) {
                return page;
            }
        }
        throw new IllegalArgumentException("MainPages not has this pageId " + id);
    }

    /**
     * 只适用于每个Pager都是不同的Fragment
     *
     * @param clazz Fragment对应的clazz
     * @return pagerId ，没有则返回-1
     */
    public int getIdByClazz(Class<? extends Fragment> clazz) {
        for (FragmentInfo page : mPages) {
            if (page.getClazz() == clazz) {
                return page.getPagerId();
            }
        }
        return -1;
    }

}
