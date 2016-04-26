package com.ztiany.customview.scroll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.customview.R;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_scroll);
        if (savedInstanceState == null) {
            replace(OverScrollFragment.newInstance());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_scroller: {
                replace(ScrollerFragment.newInstance());
                return true;
            }
            case R.id.menu_over_scroller: {
                replace(OverScrollFragment.newInstance());
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.act_basic_container_fl, fragment, fragment.getClass().getName())
                .commit();
    }
}
