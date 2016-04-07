package com.ztiany.android.bean;

import android.app.Activity;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-07 15:37                                                       <br/>
 * description                                                                             <br/>
 * vsersion
 */
public class ListItemInfo {
    private Class<? extends Activity> mClazz;
    private String infoName;

    public String getInfoName() {
        return infoName;
    }

    public ListItemInfo(Class<? extends Activity> clazz, String infoName) {
        this.mClazz = clazz;
        this.infoName = infoName;
    }

    public Class<? extends Activity> getClazz() {
        return mClazz;
    }

}
