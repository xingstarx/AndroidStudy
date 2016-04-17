package com.ztiany.android3.loader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AppListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, AppListFragment.newInstance(), AppListFragment.class.getName())
                    .commit();
        }

        Log.d("AppListActivity", "getLoaderManager():" + getLoaderManager());
        Log.d("AppListActivity", "getSupportLoaderManager():" + getSupportLoaderManager());
    }
}
