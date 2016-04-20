package com.ztiany.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.MainFragment;
import com.ztiany.customview.canvas.CanvasActivity;
import com.ztiany.customview.scroll.ScrollActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Pair<String, Class<? extends Activity>>> mMData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "nothing", Snackbar.LENGTH_LONG)
                            .setAction("exit", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            }).show();
                }
            });
        }
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName());
        if (mainFragment != null) {
            mainFragment.setData(getData());

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

    public List<Pair<String, Class<? extends Activity>>> getData() {
        mMData = new ArrayList<>();
        add(R.string.scroll, ScrollActivity.class);
        add(R.string.canvas, CanvasActivity.class);
        return mMData;
    }

    private void add(int strId, Class<? extends Activity> act) {
        mMData.add(new Pair<String, Class<? extends Activity>>(ResourceUtil.getString(strId), act));
    }
}
