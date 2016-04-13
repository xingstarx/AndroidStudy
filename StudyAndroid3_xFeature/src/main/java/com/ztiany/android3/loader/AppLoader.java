package com.ztiany.android3.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.android.base.utils.java.Checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-13 10:02                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class AppLoader extends AsyncTaskLoader<List<AppEntity>> {
    AppListFragment.InterestingConfigChanges mInterestingConfigChanges = new AppListFragment.InterestingConfigChanges();
    private static final String TAG = AppLoader.class.getSimpleName();
    private PackageManager mPackageManager;
    private final AppComparator mAppComparator = new AppComparator();
    private List<AppEntity> mApps;
    private PackageIntentReceiver mPackageReceiver;

    public AppLoader(Context context) {
        super(context);
        mPackageManager = context.getPackageManager();
    }

    @Override
    public List<AppEntity> loadInBackground() {

        return loadAppList();
    }

    private List<AppEntity> loadAppList() {
        List<ApplicationInfo> installedApplications = mPackageManager.getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES);
        if (Checker.isEmpty(installedApplications)) {
            installedApplications = Collections.emptyList();
        }

        List<AppEntity> appEntities = new ArrayList<>();
        AppEntity entry;
        for (ApplicationInfo app : installedApplications) {
            entry = new AppEntity(app);
            entry.initInfos(getContext(), mPackageManager);
            appEntities.add(entry);
        }
        Collections.sort(appEntities, mAppComparator);
        return appEntities;

    }


    private class AppComparator implements Comparator<AppEntity> {
        @Override
        public int compare(AppEntity lhs, AppEntity rhs) {
            if (lhs == rhs) {
                return 0;
            }
            return lhs.getAppName().compareTo(rhs.getAppName());
        }
    }


    @Override
    public void deliverResult(List<AppEntity> data) {
        if (isReset()) {
            if (!Checker.isEmpty(mApps)) {
                onReleaseResources(data);
            }
        }
        List<AppEntity> oldData = data;
        mApps = data;
        if (isStarted()) {
            super.deliverResult(mApps);
        }
        if (!Checker.isEmpty(oldData)) {
            onReleaseResources(oldData);
        }
    }

    protected void onReleaseResources(List<AppEntity> apps) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }

    @Override
    protected void onStartLoading() {
        if (!Checker.isEmpty(mApps)) {
            deliverResult(mApps);
        }

        if (mPackageReceiver == null)
            mPackageReceiver = new PackageIntentReceiver(this);

        boolean isChanged = mInterestingConfigChanges.applyNewConfig(getContext().getResources());

        if (takeContentChanged() || Checker.isEmpty(mApps) || isChanged) {
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(List<AppEntity> apps) {
        super.onCanceled(apps);

        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(apps);
    }


    @Override
    protected void onReset() {
        stopLoading();

        if (mPackageReceiver != null) {
            getContext().unregisterReceiver(mPackageReceiver);
            mPackageReceiver = null;
        }

        if (!Checker.isEmpty(mApps)) {
            onReleaseResources(mApps);
            mApps = null;
        }


    }
}
