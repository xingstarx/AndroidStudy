package com.ztiany.library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;




/*
 <receiver android:name="com.library.receiver.NetStateReceiver">
 <intent-filter>
 <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
 <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
 <action android:name="android.net.wifi.STATE_CHANGE" />
 </intent-filter>
 </receiver>

 网络装听，N之前可用

 */

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2015-12-08 14:50
 *         description 网络监听，接下来会使用eventBus来分发网络状态，对网络状态感兴趣的可以对网络状态进行订阅
 *         vsersion
 */
public class NetStateReceiver extends BroadcastReceiver {


    private static final String TAG = NetStateReceiver.class.getSimpleName();
    public final static int STATUS_WIFI = 2;
    public final static int STATUS_GPRS = 1;
    public final static int STATUS_ERROR = 0;
    private static int mStatus = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        int tempStatus = -1;
        // 获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        State state_wifi = null;
        State state_gprs = null;
        try {
            state_wifi = connManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).getState(); // 获取网络连接状态
        } catch (Exception e) {
            Log.d(TAG, "测试机没有WIFI模块");
        }
        try {
            state_gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState(); // 获取网络连接状态
        } catch (Exception e) {
            Log.d(TAG, "测试机没有GPRS模块");
        }
        if (null != state_wifi && State.CONNECTED == state_wifi) { // 判断是否正在使用WIFI网络
            tempStatus = STATUS_WIFI;
            Log.d(TAG, "mStatus=" + mStatus + "改变后的网络为WIFI");
        } else if (null != state_gprs && State.CONNECTED == state_gprs) { // 判断是否正在使用GPRS网络
            tempStatus = STATUS_GPRS;
            Log.d(TAG, "mStatus=" + mStatus + "改变后的网络为GPRS");
        } else {
            tempStatus = STATUS_ERROR;
            Log.d(TAG, "mStatus=" + mStatus + "改变后的网络为ERROR");
        }

        if (mStatus != tempStatus) {
            Log.d(TAG, "mStatus与改变后的网络不同，网络真的改变了");
            //TODO 通知网络状态已更新
        } else {
            Log.d(TAG, "mStatus与改变后的网络相同，不处理");
        }
        mStatus = tempStatus;


    }


}
