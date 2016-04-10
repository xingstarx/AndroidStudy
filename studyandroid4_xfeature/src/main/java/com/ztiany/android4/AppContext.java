package com.ztiany.android4;

import android.app.Application;

import com.android.base.utils.BaseUtils;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-10 20:42      <br/>
 * Description：
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtils.init(this);

    }
}
