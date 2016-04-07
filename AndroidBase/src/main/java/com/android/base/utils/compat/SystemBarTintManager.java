package com.android.base.utils.compat;
/*
 * Copyright (C) 2013 readyState Software Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Class to manage status and navigation bar tint effects when using KitKat
 * translucent system UI modes.
 * 留着借鉴
 */
class SystemBarTintManager {

    static {
        // Android allows a system property to override the presence of the navigation bar.
        // Used by the emulator.
        // See https://github.com/android/platform_frameworks_base/blob/master/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java#L1076
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                sNavBarOverride = null;
            }
        }
    }


    /**
     * The default system bar tint color value.
     */
    public static final int DEFAULT_TINT_COLOR = 0x99000000;
    public static int DEFAULT_ACTION_BAR_SIZE_Attr_RES_ID = android.R.attr.actionBarSize;

    private static String sNavBarOverride;

    private final SystemBarConfig mConfig;
    private boolean mStatusBarAvailable;
    private boolean mNavBarAvailable;
    private boolean mStatusBarTintEnabled;
    private boolean mNavBarTintEnabled;
    private View mStatusBarTintView;
    private View mNavBarTintView;
    private boolean attachDirectView;

    /**
     * Constructor. Call this in the host activity onCreate method after its
     * content view has been set. You should always create new instances when
     * the host activity is recreated. It only solve android.R.attr.actionBarSize
     *
     * @param activity The host activity.
     */
    @TargetApi(19)
    public SystemBarTintManager(Activity activity) {
        this(activity, DEFAULT_ACTION_BAR_SIZE_Attr_RES_ID);
    }

    /**
     * Constructor. Call this in the host activity onCreate method after its
     * content view has been set. You should always create new instances when
     * the host activity is recreated. It compalite with toolbar in support v7 R.attr.actionBarSize
     *
     * @param activity          The host activity.
     * @param actionBarSizeAttr The ActionBar size attr
     */
    @TargetApi(19)
    public SystemBarTintManager(Activity activity, int actionBarSizeAttr) {
        this(activity, actionBarSizeAttr, null);
    }


    @TargetApi(19)
    public SystemBarTintManager(Activity activity, int actionBarSizeAttr, View statusBarView) {
        Window win = activity.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // check theme attrs
            int[] attrs = {android.R.attr.windowTranslucentStatus,
                    android.R.attr.windowTranslucentNavigation};
            TypedArray a = activity.obtainStyledAttributes(attrs);
            try {
                mStatusBarAvailable = a.getBoolean(0, false);
                mNavBarAvailable = a.getBoolean(1, false);
            } finally {
                a.recycle();
            }

            // check window flags
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if ((winParams.flags & bits) != 0) {
                mStatusBarAvailable = true;
            }
            bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if ((winParams.flags & bits) != 0) {
                mNavBarAvailable = true;
            }
        }

        mConfig = new SystemBarConfig(activity, mStatusBarAvailable, mNavBarAvailable, actionBarSizeAttr);
        // device might not have virtual navigation keys
        if (!mConfig.hasNavigtionBar()) {
            mNavBarAvailable = false;
        }

        if (mStatusBarAvailable) {
            setupStatusBarView(activity, decorViewGroup, statusBarView);
        }
        if (mNavBarAvailable) {
            setupNavBarView(activity, decorViewGroup);
        }
    }

    /**
     * Enable tinting of the system navigation bar.
     * <p/>
     * If the platform does not have soft navigation keys, is running Jelly Bean
     * or earlier, or translucent system UI modes have not been enabled in either
     * the theme or via window flags, then this method does nothing.
     *
     * @param enabled True to enable tinting, false to disable it (default).
     */
    public void setNavigationBarTintEnabled(boolean enabled) {
        mNavBarTintEnabled = enabled;
        if (mNavBarAvailable) {
            mNavBarTintView.setVisibility(enabled ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Apply the specified color tint to all system UI bars.
     *
     * @param color The color of the background tint.
     */
    public void setTintColor(int color) {
        setStatusBarTintColor(color);
        setNavigationBarTintColor(color);
    }

    /**
     * Apply the specified drawable or color resource to all system UI bars.
     *
     * @param res The identifier of the resource.
     */
    public void setTintResource(int res) {
        setStatusBarTintResource(res);
        setNavigationBarTintResource(res);
    }

    /**
     * Apply the specified drawable to all system UI bars.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    public void setTintDrawable(Drawable drawable) {
        setStatusBarTintDrawable(drawable);
        setNavigationBarTintDrawable(drawable);
    }

    /**
     * Apply the specified alpha to all system UI bars.
     *
     * @param alpha The alpha to use
     */
    public void setTintAlpha(float alpha) {
        setStatusBarAlpha(alpha);
        setNavigationBarAlpha(alpha);
    }

    /**
     * Apply the specified color tint to the system status bar.
     *
     * @param color The color of the background tint.
     */
    public void setStatusBarTintColor(int color) {
        if (mStatusBarAvailable) {
            mStatusBarTintView.setBackgroundColor(color);
        }
    }

    /**
     * Apply the specified drawable or color resource to the system status bar.
     *
     * @param res The identifier of the resource.
     */
    public void setStatusBarTintResource(int res) {
        if (mStatusBarAvailable) {
            mStatusBarTintView.setBackgroundResource(res);
        }
    }

    /**
     * Apply the specified drawable to the system status bar.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    @SuppressWarnings("deprecation")
    public void setStatusBarTintDrawable(Drawable drawable) {
        if (mStatusBarAvailable) {
            mStatusBarTintView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * Apply the specified alpha to the system status bar.
     *
     * @param alpha The alpha to use
     */
    public void setStatusBarAlpha(float alpha) {
        mStatusBarTintView.setAlpha(alpha);
    }

    /**
     * Apply the specified color tint to the system navigation bar.
     *
     * @param color The color of the background tint.
     */
    public void setNavigationBarTintColor(int color) {
        if (mNavBarAvailable) {
            mNavBarTintView.setBackgroundColor(color);
        }
    }

    /**
     * Apply the specified drawable or color resource to the system navigation bar.
     *
     * @param res The identifier of the resource.
     */
    public void setNavigationBarTintResource(int res) {
        if (mNavBarAvailable) {
            mNavBarTintView.setBackgroundResource(res);
        }
    }

    /**
     * Apply the specified drawable to the system navigation bar.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    @SuppressWarnings("deprecation")
    public void setNavigationBarTintDrawable(Drawable drawable) {
        if (mNavBarAvailable) {
            mNavBarTintView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * Apply the specified alpha to the system navigation bar.
     *
     * @param alpha The alpha to use
     */
    public void setNavigationBarAlpha(float alpha) {
        mNavBarTintView.setAlpha(alpha);
    }

    /**
     * Get the system bar configuration.
     *
     * @return The system bar configuration for the current device configuration.
     */
    public SystemBarConfig getConfig() {
        return mConfig;
    }

    /**
     * Is tinting enabled for the system status bar?
     *
     * @return True if enabled, False otherwise.
     */
    public boolean isStatusBarTintEnabled() {
        return mStatusBarTintEnabled;
    }

    /**
     * Enable tinting of the system status bar.
     * <p/>
     * If the platform is running Jelly Bean or earlier, or translucent system
     * UI modes have not been enabled in either the theme or via window flags,
     * then this method does nothing.
     *
     * @param enabled True to enable tinting, false to disable it (default).
     */
    public void setStatusBarTintEnabled(boolean enabled) {
        mStatusBarTintEnabled = enabled;
        if (mStatusBarAvailable) {
            mStatusBarTintView.setVisibility(enabled ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Is tinting enabled for the system navigation bar?
     *
     * @return True if enabled, False otherwise.
     */
    public boolean isNavBarTintEnabled() {
        return mNavBarTintEnabled;
    }

    private void setupStatusBarView(Context context, ViewGroup decorViewGroup, View statusBarView) {
        if (statusBarView == null) {
            mStatusBarTintView = new View(context);
            FrameLayout.LayoutParams mStatusBarParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                    , mConfig.getStatusBarHeight());
            mStatusBarParams.gravity = Gravity.TOP;
            mStatusBarTintView.setLayoutParams(mStatusBarParams);
            if (mNavBarAvailable && !mConfig.isNavigationAtBottom()) {
                mStatusBarParams.rightMargin = mConfig.getNavigationBarWidth();
            }
            decorViewGroup.addView(mStatusBarTintView);
            attachDirectView = true;
        } else {
            mStatusBarTintView = statusBarView;
            ViewGroup.LayoutParams mStatusBarParams = mStatusBarTintView.getLayoutParams();
            if (mStatusBarParams == null) {
                throw new IllegalStateException("view must attach to parent view");
            }
            mStatusBarParams.height = mConfig.getStatusBarHeight();
            mStatusBarTintView.setLayoutParams(mStatusBarParams);
            attachDirectView = false;
        }
        mStatusBarTintView.setBackgroundColor(DEFAULT_TINT_COLOR);
        mStatusBarTintView.setVisibility(View.GONE);
    }

    private void updateStatusBarView() {
        ViewGroup.LayoutParams params = mStatusBarTintView.getLayoutParams();
        if (params instanceof FrameLayout.LayoutParams && attachDirectView) {
            if (mNavBarAvailable && !mConfig.isNavigationAtBottom()) {
                ((FrameLayout.LayoutParams) params).rightMargin = mConfig.getNavigationBarWidth();
            } else {
                ((FrameLayout.LayoutParams) params).rightMargin = 0;
            }
        }
    }

    private void setupNavBarView(Context context, ViewGroup decorViewGroup) {
        mNavBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        if (mConfig.isNavigationAtBottom()) {
            params.height = mConfig.getNavigationBarHeight();
            params.gravity = Gravity.BOTTOM;
        } else {
            params.width = mConfig.getNavigationBarWidth();
            params.gravity = Gravity.RIGHT;
        }
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(DEFAULT_TINT_COLOR);
        mNavBarTintView.setVisibility(View.GONE);
        decorViewGroup.addView(mNavBarTintView);
    }

    private void updateNavBarView() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mNavBarTintView.getLayoutParams();
        if (mConfig.isNavigationAtBottom()) {
            params.width = FrameLayout.LayoutParams.MATCH_PARENT;
            params.height = mConfig.getNavigationBarHeight();
            params.gravity = Gravity.BOTTOM;
        } else {
            params.width = mConfig.getNavigationBarWidth();
            params.height = FrameLayout.LayoutParams.MATCH_PARENT;
            params.gravity = Gravity.RIGHT;
        }
        mNavBarTintView.setLayoutParams(params);
    }

    /**
     * update Config when Activity or Fragment onConfigurationChanged(Configuration newConfig)
     */
    public void notifyConfigureChange() {
        mConfig.notifyConfigureChange();
        if (mStatusBarAvailable) {
            updateStatusBarView();
        }
        if (mNavBarAvailable) {
            updateNavBarView();
        }
    }

    /**
     * Class which describes system bar sizing and other characteristics for the current
     * device configuration.
     */
    public static class SystemBarConfig {

        private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
        private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
        private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
        private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
        private static final String SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar";

        private boolean mTranslucentStatusBar;
        private boolean mTranslucentNavBar;
        private int mStatusBarHeight;
        private int mActionBarHeight;
        private boolean mHasNavigationBar;
        private int mNavigationBarHeight;
        private int mNavigationBarWidth;
        private boolean mInPortrait;
        private float mSmallestWidthDp;
        private int mSplitActionBarHeight;
        private boolean mHasSplitActionBar;
        private Activity mActivity;
        private int actionBarSizeAttr;


        protected SystemBarConfig(Activity activity, boolean translucentStatusBar, boolean traslucentNavBar, int actionBarSizeAttr) {
            this.mActivity = activity;
            this.actionBarSizeAttr = actionBarSizeAttr;
            mTranslucentStatusBar = translucentStatusBar;
            mTranslucentNavBar = traslucentNavBar;
            notifyConfigureChange();
        }

        private void notifyConfigureChange() {
            Resources res = mActivity.getResources();
            mInPortrait = (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
            mSmallestWidthDp = getSmallestWidthDp(mActivity);
            mStatusBarHeight = getInternalDimensionSize(res, STATUS_BAR_HEIGHT_RES_NAME);
            mActionBarHeight = getActionBarHeight(mActivity);
            mNavigationBarHeight = getNavigationBarHeight(mActivity);
            mNavigationBarWidth = getNavigationBarWidth(mActivity);
            mHasNavigationBar = (mNavigationBarHeight > 0);
            mHasSplitActionBar = isSplitActionBar(mActivity) && !isTablet(mActivity);
            mSplitActionBarHeight = mHasSplitActionBar ? mActionBarHeight : 0;
        }

        public boolean isTablet(Context context) {
            return (context.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        }

        private int getActionBarHeight(Activity context) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(actionBarSizeAttr, tv, true);
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }

        private int getNavigationBarHeight(Activity context) {
            Resources res = context.getResources();
            int result = 0;
            if (hasNavBar(context)) {
                String key;
                if (mInPortrait) {
                    key = NAV_BAR_HEIGHT_RES_NAME;
                } else {
                    key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME;
                }
                return getInternalDimensionSize(res, key);
            }
            return result;
        }

        /**
         * test if the Activity has set android:uiOptions="splitActionBarWhenNarrow" in AndroidManifest.xml
         */
        private boolean isSplitActionBar(Activity activity) {
            try {
                Field mActivityInfo = findActivityInfoField(activity, "mActivityInfo");
                mActivityInfo.setAccessible(true);
                ActivityInfo info = (ActivityInfo) mActivityInfo.get(activity);
                if (info.uiOptions == ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW) {
                    return true;
                }
            } catch (Exception ignored) {
            }
            return false;
        }

        /**
         * get ActivityInfo form Activity only use Field , because it can't get directory
         */
        private Field findActivityInfoField(Activity activity, String fieldName) {
            for (Class<?> clazz = activity.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    return clazz.getDeclaredField(fieldName);
                } catch (Exception ignored) {
                }
            }
            // never run here :P
            return null;
        }


        private int getNavigationBarWidth(Context context) {
            Resources res = context.getResources();
            if (hasNavBar(context)) {
                return getInternalDimensionSize(res, NAV_BAR_WIDTH_RES_NAME);
            }
            return 0;
        }

        private boolean hasNavBar(Context context) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier(SHOW_NAV_BAR_RES_NAME, "bool", "android");
            if (resourceId != 0) {
                boolean hasNav = res.getBoolean(resourceId);
                // check override flag (see static block)
                if ("1".equals(sNavBarOverride)) {
                    hasNav = false;
                } else if ("0".equals(sNavBarOverride)) {
                    hasNav = true;
                }
                return hasNav;
            } else { // fallback
                return !ViewConfiguration.get(context).hasPermanentMenuKey();
            }
        }

        private int getInternalDimensionSize(Resources res, String key) {
            int result = 0;
            int resourceId = res.getIdentifier(key, "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
            return result;
        }

        @SuppressLint("NewApi")
        private float getSmallestWidthDp(Activity activity) {
            DisplayMetrics metrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            } else {
                // TODO this is not correct, but we don't really care pre-kitkat
                activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            }
            float widthDp = metrics.widthPixels / metrics.density;
            float heightDp = metrics.heightPixels / metrics.density;
            return Math.min(widthDp, heightDp);
        }

        /**
         * Should a navigation bar appear at the bottom of the screen in the current
         * device configuration? A navigation bar may appear on the right side of
         * the screen in certain configurations.
         *
         * @return True if navigation should appear at the bottom of the screen, False otherwise.
         */
        public boolean isNavigationAtBottom() {
            return (mSmallestWidthDp >= 600 || mInPortrait);
        }

        /**
         * Get the height of the system status bar.
         *
         * @return The height of the status bar (in pixels).
         */
        public int getStatusBarHeight() {
            return mStatusBarHeight;
        }

        /**
         * Get the height of the action bar.
         *
         * @return The height of the action bar (in pixels).
         */
        public int getActionBarHeight() {
            return mActionBarHeight;
        }

        /**
         * Does this device have a system navigation bar?
         *
         * @return True if this device uses soft key navigation, False otherwise.
         */
        public boolean hasNavigtionBar() {
            return mHasNavigationBar;
        }

        /**
         * Get the height of the system navigation bar.
         *
         * @return The height of the navigation bar (in pixels). If the device does not have
         * soft navigation keys, this will always return 0.
         */
        public int getNavigationBarHeight() {
            return mNavigationBarHeight;
        }

        /**
         * Get the width of the system navigation bar when it is placed vertically on the screen.
         *
         * @return The width of the navigation bar (in pixels). If the device does not have
         * soft navigation keys, this will always return 0.
         */
        public int getNavigationBarWidth() {
            return mNavigationBarWidth;
        }

        /**
         * Get the layout inset for any system UI that appears at the top of the screen.
         *
         * @param withActionBar True to include the height of the action bar, False otherwise.
         * @return The layout inset (in pixels).
         */
        public int getPixelInsetTop(boolean withActionBar) {
            return (mTranslucentStatusBar ? mStatusBarHeight : 0) + (withActionBar ? mActionBarHeight : 0);
        }

        /**
         * Get the layout inset for any system UI that appears at the bottom of the screen.
         *
         * @return The layout inset (in pixels).
         */
        public int getPixelInsetBottom() {
            if (mTranslucentNavBar && isNavigationAtBottom()) {
                return mHasSplitActionBar && mInPortrait ? mNavigationBarHeight + mSplitActionBarHeight : mNavigationBarHeight;
            } else {
                return 0;
            }
        }

        /**
         * Get the layout inset for any system UI that appears at the right of the screen.
         *
         * @return The layout inset (in pixels).
         */
        public int getPixelInsetRight() {
            if (mTranslucentNavBar && !isNavigationAtBottom()) {
                return mNavigationBarWidth;
            } else {
                return 0;
            }
        }

    }

}
