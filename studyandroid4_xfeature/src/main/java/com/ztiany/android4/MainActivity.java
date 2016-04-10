package com.ztiany.android4;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.android4.features.GridLayoutFragment;
import com.ztiany.android4.features.PopupMenuFragment;
import com.ztiany.android4.features.SpaceFragment;
import com.ztiany.android4.features.SwitchButtonFragment;
import com.ztiany.android4.features.TextureViewFragment;

/**
 * See http://www.jianshu.com/p/7ca6525fddcb
 */
public class MainActivity extends AppCompatActivity {

    private int mCurrentId;
    public static final String ID_KEY = "id_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.act_main_container_rl, SwitchButtonFragment.newInstance(), SwitchButtonFragment.class.getName())
                    .commit();
            mCurrentId = R.id.menu_switch;
        } else {
            mCurrentId = savedInstanceState.getInt(ID_KEY, -1);
        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ID_KEY, mCurrentId);
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == mCurrentId) {
            return super.onOptionsItemSelected(item);
        }
        mCurrentId = itemId;
        switch (itemId) {
            case R.id.menu_switch: {
                replace(SwitchButtonFragment.newInstance());
                break;
            }
            case R.id.menu_space: {
                replace(SpaceFragment.newInstance());

                break;
            }
            case R.id.menu_pop_menu: {
                replace(PopupMenuFragment.newInstance());
                break;
            }
            case R.id.menu_grid_layout: {
                replace(GridLayoutFragment.newInstance());

                break;
            }
            case R.id.menu_texture_view: {
                replace(TextureViewFragment.newInstance());

                break;
            }
            default: {
                break;
            }
        }


        return super.onOptionsItemSelected(item);
    }


    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.act_main_container_rl,fragment,fragment.getClass().getName())
                .commit();
    }
}
