package com.android.base.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2016-03-18 15:22
 *         description
 *         vsersion
 */
public abstract class BaseActivity extends AppCompatActivity {


    private static final String TAG = BaseActivity.class.getSimpleName();
    private boolean printLifeCycle = false;


    protected void debugLifecycle() {
        printLifeCycle = true;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onCreate" + "bundle = " + savedInstanceState);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (printLifeCycle)
            Log.d(TAG, this.getClass().getSimpleName() + "---->onDestroy");
    }




    public <T extends View> T findView(@IdRes int viewId) {
        @SuppressWarnings("unchecked")//需要什么类型，就返回什么类型
                T view = (T) findViewById(viewId);
        return view;
    }

}
