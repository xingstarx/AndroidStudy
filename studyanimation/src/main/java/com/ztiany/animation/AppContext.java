package com.ztiany.animation;

import android.app.Application;

import com.android.base.utils.BaseUtils;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-10 14:14      <br/>
 * Description：
 */
public class AppContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtils.init(this);
    }
}
