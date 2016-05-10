package com.ztiany.customview.canvas.paint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.android.base.utils.compat.AppVersion;
import com.android.base.utils.phototaker.PhotoParams;
import com.android.base.utils.phototaker.PhotoUtils;
import com.android.base.utils.view.ToastUtil;
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
        ToastUtil.showToast("select a photo");

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
        if (AppVersion.requireInculde(23)) {
            int hasWriteContactsPermission = getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
            }
        }

        if (AppVersion.requireInculde(23)) {
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

        View.OnClickListener effectClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCanvas == null) {
                    return;
                }

                float[] matrix = null;

                switch (v.getId()) {
                    case R.id.effect1: {
                        matrix = effect1;
                        break;
                    }
                    case R.id.effect2: {
                        matrix = effect2;

                        break;
                    }
                    case R.id.effect3: {
                        matrix = effect3;

                        break;
                    }
                    case R.id.effect4: {
                        matrix = effect4;

                        break;
                    }
                    case R.id.effect5: {
                        matrix = effect5;
                        break;
                    }
                    case R.id.effect6: {
                        setBitmapPixels(mBitmap, mDesBitmap);
                        mImageView.setImageBitmap(mDesBitmap);
                        return;
                    }
                }
                if (matrix == null) {
                    return;
                }
                mColorMatrix.set(matrix);
                ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(mColorMatrix);
                mPaint.setColorFilter(colorMatrixColorFilter);
                mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);
                mImageView.setImageBitmap(mDesBitmap);
            }
        };

        findView(R.id.effect1).setOnClickListener(effectClickListener);
        findView(R.id.effect2).setOnClickListener(effectClickListener);
        findView(R.id.effect3).setOnClickListener(effectClickListener);
        findView(R.id.effect4).setOnClickListener(effectClickListener);
        findView(R.id.effect5).setOnClickListener(effectClickListener);
        findView(R.id.effect6).setOnClickListener(effectClickListener);

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
        Log.d(TAG, "progress:" + progress);
        if (mCanvas == null) {
            return;
        }


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
                mRed * 1.0F / 128, 0, 0, 0, 0,
                0, mGreen * 1.0F / 128, 0, 0, 0,
                0, 0, mBlue * 1.0F / 128, 0, 0,
                0, 0, 0, mAlpha * 1.0F / 128, 0,
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

    float[] effect1 = {
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0, 0, 0, 1, 0,
    };
    float[] effect2 = {
            -1, 0, 0, 1, 1,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0,
    };
    float[] effect3 = {
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    };
    float[] effect4 = {
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            0, 0, 0, 1, 0,
    };
    float[] effect5 = {
            1.438F, -0.122F, -0.016F, 0, -0.03F,
            -0.62F, 1.378F, -0.16F, 0, -0.05F,
            -0.062F, -0.122F, 1.438F, 0, -0.02F,
            0, 0, 0, 1, 0,
    };


    private void setBitmapPixels(Bitmap bitmap, Bitmap desBitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int length = pixels.length;
        int color;
        int r, g, b, a;
        for (int i = 0; i < length; i++) {
            color = pixels[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            r = r > 255 ? 255 : r < 0 ? 0 : r;
            g = g > 255 ? 255 : g < 0 ? 0 : g;
            b = b > 255 ? 255 : b < 0 ? 0 : b;
            a = a > 255 ? 255 : a < 0 ? 0 : a;
            pixels[i] = Color.argb(a, r, g, b);
        }
        desBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    }

}
