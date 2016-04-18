package com.ztiany.customview;

import android.app.Application;

import com.android.base.utils.BaseUtils;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-18 13:08                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtils.init(this);

    }
}
