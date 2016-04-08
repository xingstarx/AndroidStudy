package com.ztiany.android.drawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.android.base.fragment.FragmentInfo;
import com.android.base.ui.BaseActivity;
import com.android.base.utils.android.ResourceUtil;
import com.android.base.utils.compat.SystemBarCompat;
import com.ztiany.android.MainActivity;
import com.ztiany.android.R;

public class DrawerActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    DrawerLayout mDrawerLayout;
    FrameLayout mFrameLayout;
    Toolbar mToolbar;
    protected NavigationView mNavigationView;

    private static final String CURRENT_PAGE_KEY = "currentPageId";
    protected int mCurrentPagerId;

    private MainPages mMainPages;

    private ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        init(savedInstanceState);
        setupViews();
    }

    protected void init(Bundle savedInstanceState) {
        mMainPages = MainPages.getMainPages();
    }


    protected void setupViews() {
        findViews();
        setStatusBar();
        initToolBar();
        initDrawer();
        initNavigation();
    }

    private void findViews() {
        mDrawerLayout = findView(R.id.act_main_drawer);
        mFrameLayout = findView(R.id.act_main_sfl);
        mToolbar = findView(R.id.toolbar);
        mNavigationView = findView(R.id.act_main_drawer_nav);
    }

    private void setStatusBar() {
        SystemBarCompat.setTranslucentForKitkat(this);
        SystemBarCompat.setStatusBarColorForKitkat(this, ResourceUtil.getColor(R.color.colorPrimary, this));

    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示actonBar左边的home按钮
            getSupportActionBar().setHomeButtonEnabled(true);//启动actonBar左边的的启用或禁用“home”按钮
        }
    }

    private void initDrawer() {
        //ActionBarDrawerToggle实现了DrawerListener
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //        能将drawerLayout的展开和隐藏与actionbar的app 图标关联起来
        mActionBarDrawerToggle.syncState();
    }

    private void initNavigation() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.id_menu_setting: {
                        break;
                    }
                    case R.id.id_menu_nav_about: {
                        break;
                    }
                    case R.id.id_menu_nav_detail: {

                        return false;
                    }
                    default: {
                        if (mCurrentPagerId != itemId) {
                            switchPage(itemId);
                        }
                        closeDrawer();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        FragmentInfo page = mMainPages.homePage();
        if (savedInstanceState == null) {
            mCurrentPagerId = page.getPagerId();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.act_main_container_rl, page.newFragment(this), page.getTag())
                    .commit();
        } else {
            mCurrentPagerId = savedInstanceState.getInt(CURRENT_PAGE_KEY);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_PAGE_KEY, mCurrentPagerId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    private void switchPage(int itemId) {
        Log.d(TAG, "switchPage() called with: " + "itemId = [" + itemId + "]");
        mCurrentPagerId = itemId;
        FragmentInfo page = mMainPages.formPageId(itemId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d(TAG, page.getTag());

        Fragment fragment = fragmentManager.findFragmentByTag(page.getTag());
        if (fragment != null) {
            /*Log.d(TAG, "fragment " + fragment);
            FragmentManager.BackStackEntry backStackEntryAt = null;
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            int index = 0;
            for (int i = 0; i < backStackEntryCount; i++) {
                backStackEntryAt = fragmentManager.getBackStackEntryAt(i);
                ++index;
                if (page.isToStack() && page.getStackName().equals(backStackEntryAt.getName())) {
                    break;
                }
            }
            Log.d(TAG, "index:" + index);
            for (int i = 0; i < index; i++) {
                fragmentManager.popBackStackImmediate();
            }*/
            if (page.getStackName() == null) {
                fragmentManager.popBackStackImmediate(0,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }else {
                fragmentManager.popBackStackImmediate(page.getStackName(),0);
            }

        } else {

            Log.d(TAG, "fragment == null");
            FragmentTransaction transaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.act_main_container_rl, page.newFragment(this), page.getTag());
            if (page.isToStack()) {
                transaction.addToBackStack(page.getStackName());
            }
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
            return;
        }


        super.onBackPressed();


    }

    private void setPageTitle(int titleId) {
        if (mToolbar != null) {
            mToolbar.setTitle(titleId);
        }
    }

}
