package com.ztiany.android3.loader;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.base.adapter.recycler.RecyclerAdapter;
import com.android.base.adapter.recycler.ViewHolder;
import com.android.base.utils.android.ToastUtil;
import com.ztiany.android3.R;

import java.util.List;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-13 10:01                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class AppListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<AppEntity>> {


    private static final String TAG = AppListFragment.class.getSimpleName();
    public static final int LOADER_ID = 1;
    private AppListAdapter mAppListAdapter;
    private String mCurFilter;

    static AppListFragment newInstance() {
        return new AppListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAppListAdapter = new AppListAdapter(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_load_app, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.frag_load_app_rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAppListAdapter);

    }

    public static class InterestingConfigChanges {
        final Configuration mLastConfiguration = new Configuration();
        int mLastDensity;

        boolean applyNewConfig(Resources res) {
            int configChanges = mLastConfiguration.updateFrom(res.getConfiguration());
            boolean densityChanged = mLastDensity != res.getDisplayMetrics().densityDpi;
            if (densityChanged || (configChanges&(ActivityInfo.CONFIG_LOCALE
                    | ActivityInfoCompat.CONFIG_UI_MODE|ActivityInfo.CONFIG_SCREEN_LAYOUT)) != 0) {
                mLastDensity = res.getDisplayMetrics().densityDpi;
                return true;
            }
            return false;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem search = menu.add("search");
        search.setIcon(android.R.drawable.ic_menu_search);
        MenuItemCompat.setShowAsAction(search, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        final View view = SearchViewCompat.newSearchView(getContext());
        if (view != null) {
            SearchViewCompat.setOnQueryTextListener(view, new SearchViewCompat.OnQueryTextListenerCompat() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
                    ToastUtil.showLongToast(newText);
                    return true;
                }
            });

            SearchViewCompat.setOnCloseListener(view, new SearchViewCompat.OnCloseListenerCompat() {
                @Override
                public boolean onClose() {
                    if (!TextUtils.isEmpty(SearchViewCompat.getQuery(view))) {
                        SearchViewCompat.setQuery(view, null, true);
                    }
                    return true;
                }
            });
        }

        MenuItemCompat.setActionView(search, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);

    }

    @Override
    public Loader<List<AppEntity>> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_ID) {
            Log.d(TAG, "onCreateLoader() called with: " + "id = [" + id + "], args = [" + args + "]");
            return new AppLoader(getContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<AppEntity>> loader, List<AppEntity> data) {
        Log.d(TAG, "onLoadFinished() called with: " + "loader "+loader);
        mAppListAdapter.setData(data);
    }


    @Override
    public void onLoaderReset(Loader<List<AppEntity>> loader) {
        Log.d(TAG, "onLoaderReset() called with: " + "loader = [" + loader + "]");
        mAppListAdapter.clear();
    }


    private class AppListAdapter extends RecyclerAdapter<AppEntity, ViewHolder<AppEntity>> {


        public AppListAdapter(Context context) {
            super(context);
        }

        @Override
        public ViewHolder<AppEntity> onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mLayoutInflater.inflate(R.layout.item_app_list, parent, false);

            return new ViewHolder<AppEntity>(itemView) {

                private TextView mTextView;
                private ImageView mImageView;

                @Override
                protected void findViews() {
                    mTextView = findView(R.id.item_app_label_tv);
                    mImageView = findView(R.id.item_app_icon_iv);
                }

                @Override
                public void bindData(AppEntity data) {
                    mTextView.setText(data.getAppName());
                    mImageView.setImageDrawable(data.getIcon());

                }
            };
        }

        @Override
        public void onBindViewHolder(ViewHolder<AppEntity> viewHolder, int position) {
            viewHolder.bindData(getItem(position));
        }


    }

}
