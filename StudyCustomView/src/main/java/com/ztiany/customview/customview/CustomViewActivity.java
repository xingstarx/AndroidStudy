package com.ztiany.customview.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.InjectActivity;
import com.ztiany.MainAdapter;
import com.ztiany.MainFragment;
import com.ztiany.customview.R;
import com.ztiany.customview.canvas.dispatch.DispatchFragment;
import com.ztiany.customview.customview.largeimage.LargeImageFragment;

import java.util.Arrays;

public class CustomViewActivity extends InjectActivity implements MainAdapter.OnItemClickListener {


    @Override
    protected int layoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void setupView() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            MainFragment mainFragment = MainFragment.newInstance();
            MainAdapter mainAdapter = new MainAdapter(this,
                    Arrays.asList(ResourceUtil.getStringArray(R.array.custom_view)));
            mainAdapter.setItemOnClickListener(this);
            mainFragment.setAdapter(mainAdapter);
            replace(mainFragment);
        }
    }


    private void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_custom_view_container_rl, fragment, fragment.getClass().getName())
                .commit();
    }

    @Override
    public void onClick(int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.act_custom_view_container_rl, getFragmentPosition(position))
                .commit();
    }

    private Fragment getFragmentPosition(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = LargeImageFragment.newInstance();
                break;

        }
        return fragment;
    }

}
