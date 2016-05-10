package com.ztiany.android.withviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.android.base.adapter.pager.AdapterDelegate;
import com.android.base.adapter.pager.ViewPageFragmentAdapter;
import com.android.base.app.AbsActivity;
import com.ztiany.android.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AbsActivity {


    private ViewPager mViewPager;


    private ViewPageFragmentAdapter mViewPageFragmentAdapter =
            new ViewPageFragmentAdapter(getSupportFragmentManager(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initPageInfo();
        setupViews();
    }


    private void setupViews() {
        findViews();

        mViewPager.setAdapter(mViewPageFragmentAdapter);
        mViewPageFragmentAdapter.notifyDataSetChanged();
    }

    private void findViews() {
        mViewPager = findView(R.id.act_viewpager_vp);
    }


    private void initPageInfo() {

        List<AdapterDelegate.ViewPageInfo> pageInfoList = new ArrayList<>();
        pageInfoList.add(
                new AdapterDelegate.ViewPageInfo("TitleOne",
                        PagerOneFragment.class.getName(),
                        PagerOneFragment.class,
                        null));
        pageInfoList.add(
                new AdapterDelegate.ViewPageInfo("TitleTwo",
                        PagerTwoFragment.class.getName(),
                        PagerTwoFragment.class,
                        null));

        pageInfoList.add(
                new AdapterDelegate.ViewPageInfo("TitleThird",
                        PagerThirdFragment.class.getName(),
                        PagerThirdFragment.class,
                        null));

        pageInfoList.add(
                new AdapterDelegate.ViewPageInfo("TitleFour",
                        PagerFourFragment.class.getName(),
                        PagerFourFragment.class,
                        null));


        mViewPageFragmentAdapter.addAllTab(pageInfoList);


    }



}
