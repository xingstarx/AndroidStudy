package com.ztiany.customview.customview.largeimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.base.utils.android.UnitConverter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-10 20:55      <br/>
 * Description：
 */
public class LargeImageFragment extends Fragment {

    private LinearLayout mLinearLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mLinearLayout == null ? mLinearLayout = new LinearLayout(getContext()) : mLinearLayout;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDemo();
    }

    private void showDemo() {
        InputStream open = null;
        try {
            mLinearLayout.setOrientation(LinearLayout.VERTICAL);

            open = getContext().getAssets().open("tupian.jpg");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(open, null, options);

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            BitmapRegionDecoder brd = BitmapRegionDecoder.newInstance(open, false);

            BitmapFactory.Options optionsBrd = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = brd.decodeRegion(new Rect(0, 0, outWidth / 2, outHeight / 2), optionsBrd);
            ImageView imageView = new AppCompatImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lp.topMargin = UnitConverter.dpToPx(10);
            mLinearLayout.addView(imageView, lp);
            imageView.setImageBitmap(bitmap);


            ImageView imageViewOrigin = new AppCompatImageView(getContext());
            imageViewOrigin.setImageBitmap(BitmapFactory.decodeStream(open));
            mLinearLayout.addView(imageViewOrigin, lp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static Fragment newInstance() {
        return new LargeImageFragment();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(Menu.NONE, 1, 1, "Demo");
        menu.add(Menu.NONE, 2, 2, "LargeImageView");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mLinearLayout.removeAllViews();

        switch (item.getItemId()) {
            case 1: {
                showDemo();
                break;
            }
            case 2: {
                showLargeImage();

                break;
            }
        }
        return true;
    }

    private void showLargeImage() {
        LargeImageView largeImageView = new LargeImageView(getContext());
        mLinearLayout.addView(largeImageView);
        try {
            largeImageView.setImageStream(getContext().getAssets().open("qm.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
