package com.android.base.utils.resource;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import com.android.base.utils.BaseUtils;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-16 21:36      <br/>
 * Description：资源工具类，其他资源获取可以使用 ContextCompat
 */
public class ResourceUtil {

    private ResourceUtil() {
    }




    public static String getString(@StringRes int id) {
        return BaseUtils.getResources().getString(id);
    }

    public static String[] getStringArray(@ArrayRes int id) {
        return BaseUtils.getResources().getStringArray(id);
    }


    public static int[] getIntArray(@ArrayRes int id) {
        return BaseUtils.getResources().getIntArray(id);
    }




}
