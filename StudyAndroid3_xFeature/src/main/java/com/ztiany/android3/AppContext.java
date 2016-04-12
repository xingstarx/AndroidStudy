package com.ztiany.android3;

import android.app.Application;

import com.android.base.utils.BaseUtils;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-12 21:12      <br/>
 * Description：
 */
public class AppContext  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtils.init(this);

    }
}
