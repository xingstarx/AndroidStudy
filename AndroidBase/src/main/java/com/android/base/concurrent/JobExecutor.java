package com.android.base.concurrent;

import java.util.concurrent.Executor;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-18 0:57      <br/>
 * Description：
 */
public class JobExecutor {

    private static SmartExecutor smartExecutor;


    public static void setSchedulePolicy(SchedulePolicy policy) {
        if (smartExecutor != null) {
            smartExecutor.setSchedulePolicy(policy);
        }
    }

    public static void setOverloadPolicy(OverloadPolicy policy) {
        if (smartExecutor != null) {
            smartExecutor.setOverloadPolicy(policy);
        }
    }


    static {
        smartExecutor = new SmartExecutor();
        smartExecutor.setSchedulePolicy(SchedulePolicy.LastInFirstRun);
        smartExecutor.setOverloadPolicy(OverloadPolicy.DiscardOldTaskInQueue);
    }

    public static Executor getJobExecutor() {
        return smartExecutor;
    }

}
