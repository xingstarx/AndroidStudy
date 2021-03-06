package com.android.base.app;

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
 *         Activity生命周期执行顺序：
 *
 *          onCrate
 *          onStart
 *          onRestoreInstanceState（mayBe）
 *         onPostCreate
 *         onResume
 *         onPuase
 *         onSaveInstanceState
 *         onStop 在内存不足而导致系统无法保留此进程的情况下，onStop() 可能都不会被执行。
 *         onDestory
 *
 *         vsersion
 */
public abstract class AbsActivity extends AppCompatActivity {


    private boolean printLifeCycle = false;


    protected void debugLifecycle() {
        printLifeCycle = true;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onCreate" + "bundle = " + savedInstanceState);
        }

    }

    private String tag() {
        return this.getClass().getName();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onRestart");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onStart");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onResume");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onPause");
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onStop");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (printLifeCycle) {
            Log.d(tag(), this.getClass().getSimpleName() + "---->onDestroy");
        }
    }


    public <T extends View> T findView(@IdRes int viewId) {
        @SuppressWarnings("unchecked")//需要什么类型，就返回什么类型
                T view = (T) findViewById(viewId);
        return view;
    }

}
