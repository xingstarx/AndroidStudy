package com.android.base.utils.system;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-17 23:02      <br/>
 * Description：
 */
public class AppUtils {

    private AppUtils() {

    }

    /**
     * see https://github.com/wenmingvs/AndroidProcess
     *
     */
    public static boolean isInBackground() {

        return true;
    }


    public static void recreateActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        } else {
            Intent intent = activity.getIntent();
            intent.setClass(activity, activity.getClass());
            activity.startActivity(intent);
            activity.finish();
            activity.overridePendingTransition(0, 0);
        }
    }




}
