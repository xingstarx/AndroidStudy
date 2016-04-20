package com.ztiany.customview.canvas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.InjectActivity;
import com.ztiany.customview.R;
import com.ztiany.customview.canvas.coordinate.CoordinateFragment;
import com.ztiany.customview.canvas.dispatch.DispatchFragment;
import com.ztiany.customview.canvas.draw.CanvasDrawFragment;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 11:37      <br/>
 * Description：
 */
public class CanvasActivity extends InjectActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_canvas;
    }

    @Override
    protected void setupView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_canvas, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_coordinate: {
                replace(CoordinateFragment.newInstance());
                break;
            }
            case R.id.menu_dispatch: {
                replace(DispatchFragment.newInstance());
                break;
            }     case R.id.menu_draw: {
                replace(CanvasDrawFragment.newInstance());
                break;
            }
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState == null) {
            replace(DispatchFragment.newInstance());
        }
    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_canvas_container_fl, fragment, fragment.getClass().getName())
                .commit();
    }
}
