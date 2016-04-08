package com.ztiany.android.withviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.base.adapter.pager.ViewPageFragmentAdapter;
import com.android.base.ui.BaseActivity;
import com.ztiany.android.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {


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

    private void initPageInfo() {

        List<ViewPageFragmentAdapter.ViewPageInfo> pageInfoList = new ArrayList<>();
        pageInfoList.add(
                new ViewPageFragmentAdapter.ViewPageInfo("TitleOne",
                        PagerOneFragment.class.getName(),
                        PagerOneFragment.class,
                        null));
        pageInfoList.add(
                new ViewPageFragmentAdapter.ViewPageInfo("TitleTwo",
                        PagerTwoFragment.class.getName(),
                        PagerTwoFragment.class,
                        null));

        pageInfoList.add(
                new ViewPageFragmentAdapter.ViewPageInfo("TitleThird",
                        PagerThirdFragment.class.getName(),
                        PagerThirdFragment.class,
                        null));

        pageInfoList.add(
                new ViewPageFragmentAdapter.ViewPageInfo("TitleFour",
                        PagerFourFragment.class.getName(),
                        PagerFourFragment.class,
                        null));


        mViewPageFragmentAdapter.addAllTab(pageInfoList);

    }

    private void setupViews() {
        findViews();
    }

    private void findViews() {
        mViewPager = findView(R.id.act_viewpager_vp);
    }


    public void pagerStatusAdapter(View view) {
    }

    public void addOne2(View view) {

    }

    public void removeLast2(View view) {

    }

    public void pagerAdapter(View view) {
        mViewPager.setAdapter(mViewPageFragmentAdapter);
    }

    public void addOne1(View view) {
        mViewPageFragmentAdapter.addTab("TitleFile", PagerFiveFragment.class.getName(), PagerFiveFragment.class, null);
    }

    public void removeLast1(View view) {
        mViewPageFragmentAdapter.remove(mViewPageFragmentAdapter.getCount() - 1);
    }

    public void notify1(View view) {
        mViewPageFragmentAdapter.notifyDataSetChanged();
    }


}
