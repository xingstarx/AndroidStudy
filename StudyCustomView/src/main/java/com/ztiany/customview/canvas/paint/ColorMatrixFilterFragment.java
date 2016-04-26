package com.ztiany.customview.canvas.paint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.android.base.utils.android.ToastUtil;
import com.android.base.utils.compat.AppVersion;
import com.android.base.utils.phototaker.PhotoParams;
import com.android.base.utils.phototaker.PhotoUtils;
import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

import java.util.Arrays;

import butterknife.Bind;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-23 13:18      <br/>
 * Description：
 */
public class ColorMatrixFilterFragment extends InjectFragment implements SeekBar.OnSeekBarChangeListener {


    private PhotoUtils mPhotoUtils;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private ColorMatrix mColorMatrix;

    private static final String TAG = ColorMatrixFilterFragment.class.getSimpleName();
    private Bitmap mDesBitmap;

    private float mAlpha;
    private float mRed;
    private float mGreen;
    private float mBlue;

    public static ColorMatrixFilterFragment newInstance() {
        return new ColorMatrixFilterFragment();
    }


    @Bind(R.id.frag_color_matrix_iv)
    protected ImageView mImageView;

    @Bind(R.id.frag_color_matrix_alpha_sb)
    protected SeekBar mAlphaSb;
    @Bind(R.id.frag_color_matrix_red_sb)
    protected SeekBar mRedSb;
    @Bind(R.id.frag_color_matrix_green_sb)
    protected SeekBar mGreenSb;
    @Bind(R.id.frag_color_matrix_blue_sb)
    protected SeekBar mBlueSb;


    @Override
    protected int layoutId() {
        return R.layout.fragment_color_matrix;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoUtils = new PhotoUtils(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorMatrix = new ColorMatrix();
        checkPermission();
    }

    private void initRes() {
        mAlpha = 128;
        mRed = 128;
        mGreen = 128;
        mBlue = 128;
        mAlphaSb.setProgress(128);
        mRedSb.setProgress(128);
        mGreenSb.setProgress(128);
        mBlueSb.setProgress(128);

    }

    private void checkPermission() {
        if (AppVersion.afterM()) {
            int hasWriteContactsPermission = getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
            }
        }

        if (AppVersion.afterM()) {
            int hasWriteContactsPermission = getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoUtils.selectPicture(new PhotoParams(getContext().getExternalCacheDir()));
            }
        });

        mAlphaSb.setOnSeekBarChangeListener(this);
        mRedSb.setOnSeekBarChangeListener(this);
        mGreenSb.setOnSeekBarChangeListener(this);
        mBlueSb.setOnSeekBarChangeListener(this);

        initRes();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPhotoUtils.setOnPhotoResultListener(new PhotoUtils.OnPhotoResultListener() {
            @Override
            public void onPhotoResult(Uri uri) {
                Log.d(TAG, uri.getPath());
                mBitmap = BitmapFactory.decodeFile(uri.getPath());
                mImageView.setImageBitmap(mBitmap);
                mDesBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
                mCanvas = new Canvas(mDesBitmap);
                initRes();
            }

            @Override
            public void onPhotoCancel() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: " + "requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        mPhotoUtils.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mCanvas == null) {
            ToastUtil.showToast("select a photo");
            return;
        }
        Log.d(TAG, "progress:" + progress);

        switch (seekBar.getId()) {
            case R.id.frag_color_matrix_alpha_sb:
                mAlpha = progress;
                break;
            case R.id.frag_color_matrix_red_sb:
                mRed = progress;
                break;
            case R.id.frag_color_matrix_green_sb:
                mGreen = progress;
                break;
            case R.id.frag_color_matrix_blue_sb:
                mBlue = progress;
                break;
        }

        float[] matrix = new float[]{
                mRed / 128F, 0, 0, 0, 0,
                0, mGreen / 128F, 0, 0, 0,
                0, 0, mBlue / 128F, 0, 0,
                0, 0, 0, mAlpha / 128F, 0,
        };
        Log.d(TAG, Arrays.toString(matrix));
        mColorMatrix.set(matrix);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(mColorMatrix);
        mPaint.setColorFilter(colorMatrixColorFilter);
        mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mImageView.setImageBitmap(mDesBitmap);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
