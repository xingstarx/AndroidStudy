package com.android.base.ui;

import android.os.Bundle;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 19:53      <br/>
 * Description：
 */
public interface Presenter<V extends UI> {

    void onCreate(V view, Bundle saveInstance);

    void onCreated();

    void onResume();

    void onSaveInstance(Bundle saveInstance);

    void onPause();

    void onDestroy();

}
