package com.ztiany.customview.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.android.base.utils.android.ResourceUtil;
import com.ztiany.InjectActivity;
import com.ztiany.MainAdapter;
import com.ztiany.MainFragment;
import com.ztiany.customview.R;
import com.ztiany.customview.canvas.coordinate.CoordinateFragment;
import com.ztiany.customview.canvas.dispatch.DispatchFragment;
import com.ztiany.customview.canvas.draw.DrawingFragment;
import com.ztiany.customview.canvas.matrix.MatrixFragment;
import com.ztiany.customview.canvas.paint.ColorMatrixFilterFragment;
import com.ztiany.customview.canvas.path.AddPathFragment;
import com.ztiany.customview.canvas.path.BezierFragment;
import com.ztiany.customview.canvas.rect_region.RectWithRegionFragment;
import com.ztiany.customview.canvas.shader.ShaderFragment;

import java.util.Arrays;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 11:37      <br/>
 * Description：
 */
public class CanvasActivity extends InjectActivity implements MainAdapter.OnItemClickListener {


    @Override
    protected int layoutId() {
        return R.layout.activity_canvas;
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
                    Arrays.asList(ResourceUtil.getStringArray(R.array.canvas_potion_array)));
            mainAdapter.setItemOnClickListener(this);
            mainFragment.setAdapter(mainAdapter);
            replace(mainFragment);
        }
    }


    private void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_canvas_container_fl, fragment, fragment.getClass().getName())
                .commit();
    }

    @Override
    public void onClick(int position) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.act_canvas_container_fl, getFragmentPosition(position))
                .commit();
    }

    private Fragment getFragmentPosition(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = DispatchFragment.newInstance();
                break;
            case 1:
                fragment = CoordinateFragment.newInstance();
                break;
            case 2:
                fragment = ColorMatrixFilterFragment.newInstance();
                break;
            case 3:
                fragment = AddPathFragment.newInstance();
                break;
            case 4:
                fragment = BezierFragment.newInstance();
                break;
            case 5:
                fragment = RectWithRegionFragment.newInstance();
                break;
            case 6:
                fragment = ShaderFragment.newInstance();
                break;
            case 7:
                fragment = MatrixFragment.newInstance();
                break;
            case 8:
                fragment = DrawingFragment.newInstance();
                break;
        }
        return fragment;
    }


}
