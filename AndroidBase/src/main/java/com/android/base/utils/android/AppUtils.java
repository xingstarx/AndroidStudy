package com.android.base.utils.android;

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
     * <p>
     * public interface ActivityLifecycleCallbacks {
     * void onActivityCreated(Activity activity, Bundle savedInstanceState);
     * void onActivityStarted(Activity activity);
     * void onActivityResumed(Activity activity);
     * void onActivityPaused(Activity activity);
     * void onActivityStopped(Activity activity);
     * void onActivitySaveInstanceState(Activity activity, Bundle outState);
     * void onActivityDestroyed(Activity activity);
     * }
     * </p>
     * <p>
     * see https://github.com/wenmingvs/AndroidProcess
     *
     * @return
     */
    public static boolean isInBackground() {

        return true;
    }




}
