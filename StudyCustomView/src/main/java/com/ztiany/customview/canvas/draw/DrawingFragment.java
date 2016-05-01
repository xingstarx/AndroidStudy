package com.ztiany.customview.canvas.draw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-30 16:58      <br/>
 * Description：
 */
public class DrawingFragment extends Fragment {

    private FrameLayout mFrameLayout;

    public static DrawingFragment newInstance() {
        return new DrawingFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFrameLayout == null) {
            mFrameLayout = new FrameLayout(getContext());
            mFrameLayout.addView(new DrawTextView(getContext()));
        }
        return mFrameLayout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(Menu.NONE, 1, 0, "DrawText");
        menu.add(Menu.NONE, 2, 0, "DrawArc");
        menu.add(Menu.NONE, 3, 0, "DrawBitmap");
        menu.add(Menu.NONE, 4, 0, "DrawCircle");
        menu.add(Menu.NONE, 5, 0, "DrawOval");
        menu.add(Menu.NONE, 6, 0, "DrawPicture");
        menu.add(Menu.NONE, 7, 0, "DrawPoint");
        menu.add(Menu.NONE, 8, 0, "DrawRect");
        menu.add(Menu.NONE, 9, 0, "BitmapMesh");
        menu.add(Menu.NONE, 10, 0, "ClipCanvas");
        menu.add(Menu.NONE, 11, 0, "DashView");
        menu.add(Menu.NONE, 12, 0, "MeshView");
        menu.add(Menu.NONE, 13, 0, "SaveLayer");
        menu.add(Menu.NONE, 14, 0, "Save");
        menu.add(Menu.NONE, 15, 0, "ScaleCanvas");
        menu.add(Menu.NONE, 16, 0, "SkewCanvas");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mFrameLayout.removeAllViews();
        View view = null;
        switch (item.getItemId()) {
            case 1:
                view = new DrawTextView(getContext());
                break;
            case 2:
                view = new DrawArcView(getContext());
                break;
            case 3:
                view = new DrawBitmapView(getContext());

                break;
            case 4:
                view = new DrawCircle(getContext());

                break;
            case 5:
                view = new DrawOvalView(getContext());

                break;
            case 6:
                view = new DrawPictureView(getContext());

                break;
            case 7:
                view = new DrawPointView(getContext());

                break;
            case 8:
                view = new DrawRectView(getContext());

                break;
            case 9:
                view = new BitmapMeshView(getContext());

                break;
            case 10:
                view = new ClipCanvasView(getContext());

                break;
            case 11:
                view = new DashView(getContext());

                break;
            case 12:
                view = new MeshView(getContext());

                break;
            case 13:
                view = new SaveLayerView(getContext());

                break;
            case 14:
                view = new SaveView(getContext());

                break;
            case 15:
                view = new ScaleView(getContext());

                break;
            case 16:
                view = new SkewView(getContext());
                break;


        }

        mFrameLayout.addView(view);
        return true;
    }
}
