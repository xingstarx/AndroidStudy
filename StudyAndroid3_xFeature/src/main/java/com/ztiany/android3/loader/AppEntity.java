package com.ztiany.android3.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-13 10:02                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class AppEntity {

    private final ApplicationInfo mApplicationInfo;

    private String mAppName;
    private final File mApkFile;
    private Drawable mIcon;
    private boolean mMounted;


    public Drawable getIcon() {
        return mIcon;
    }

    public AppEntity(ApplicationInfo applicationInfo) {
        mApplicationInfo = applicationInfo;
        mApkFile = new File(mApplicationInfo.sourceDir);
    }

    public String getAppName() {
        return mAppName;
    }


    private Drawable loadIcon(Context context, PackageManager packageManager) {
        if (mIcon == null) {
            if (mApkFile.exists()) {
                mIcon = mApplicationInfo.loadIcon(packageManager);
                return mIcon;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload
            // its icon.
            if (mApkFile.exists()) {
                mMounted = true;
                mIcon = mApplicationInfo.loadIcon(packageManager);
                return mIcon;
            }
        } else {
            return mIcon;
        }

        return context.getResources().getDrawable(
                android.R.drawable.sym_def_app_icon);
    }

    public void initInfos(Context context, PackageManager packageManager) {
        if (packageManager == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mAppName = mApplicationInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mApplicationInfo.loadLabel(context.getPackageManager());
                mAppName = label != null ? label.toString() : mApplicationInfo.packageName;
            }
        }
        loadIcon(context, packageManager);
    }
}

