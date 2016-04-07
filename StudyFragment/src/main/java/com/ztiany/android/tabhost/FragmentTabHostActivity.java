package com.ztiany.android.tabhost;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.android.R;


public class FragmentTabHostActivity extends AppCompatActivity {

    private FragmentTabHost mFragmentTabHost;
    private String[] mTabNames = {
            "首页",
            "分类",
            "购物车",
            "我的"
    };
    private int[] mTabSrc = {
            R.drawable.tab_home_selector,
            R.drawable.tab_plaza_selector,
            R.drawable.tab_cart_selector,
            R.drawable.tab_my_selector
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        findView();
        initFragments();//这里不需要判断savedInstanceState
        setTabListener();
    }

    private void findView() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    private void initFragments() {

        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mFragmentTabHost.getTabWidget().setDividerDrawable(null);

        TabHost.TabSpec tabHome = mFragmentTabHost.newTabSpec("tabHome");
        tabHome.setIndicator(createTabView(0));
        mFragmentTabHost.addTab(tabHome, HomeFragment.class, null);

        TabHost.TabSpec tabPlaza = mFragmentTabHost.newTabSpec("tabPlaza");
        tabPlaza.setIndicator(createTabView(1));
        mFragmentTabHost.addTab(tabPlaza, PlazaFragment.class, null);

        TabHost.TabSpec tabShopping = mFragmentTabHost.newTabSpec("tabShopping");

        tabShopping.setIndicator(createTabView(2));
        mFragmentTabHost.addTab(tabShopping, ShoppingFragment.class, null);

        TabHost.TabSpec tabMine = mFragmentTabHost.newTabSpec("tabMine");
        tabMine.setIndicator(createTabView(3));
        mFragmentTabHost.addTab(tabMine, MineFragment.class, null);

        mFragmentTabHost.getTabWidget().setStripEnabled(false);//去掉指示线
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);//去掉tab分割线
    }

    private void setTabListener() {
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("FragmentTabHostActivity", tabId);
            }
        });
    }

    private View createTabView(int i) {
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(mTabNames[i]);
        textView.setTextColor(ResourceUtil.getColor(R.color.tab_color, this));
        Drawable drawable = createDrawable(i);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        return textView;
    }

    private Drawable createDrawable(int i) {
        int drawableId = mTabSrc[i];
        return ResourceUtil.getDrawable(drawableId, this);
    }
}
