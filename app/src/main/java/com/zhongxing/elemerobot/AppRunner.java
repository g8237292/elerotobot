package com.zhongxing.elemerobot;

import android.content.Context;
import android.content.Intent;

import com.ziguangcn.amazonsdk.AdbDevice;
import com.ziguangcn.amazonsdk.AppHelper;
import com.ziguangcn.amazonsdk.Sleeper;

/**
 * Created by kilua626 on 2018/6/29.
 */

public class AppRunner {
    public static void run(final Context context, final String packageName){
        // TODO:启动后，可能由于ANR等原因崩溃，自动重启？
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                if(launchIntent != null){
                    // 这里有时会阻塞住，所以用线程
                    context.startActivity(launchIntent);
                }
            }
        }).start();
        wait(context, packageName);
    }

    public static void wait(Context context, String packageName){
        // wait for app initialization
        Sleeper.sleep(30 * 1000);
        int maxRetry = 3, i = 0, curPid = 0, lastPid = 0;
        while (i < maxRetry) {
            // 有时亚马逊APP异常退出前台，但进程仍在后台，所以这里只能判断不在前台就跳出
            if (!AppHelper.isAppRunningInForground(context, packageName)) {
                i += 1;
                break;
            }else {
                i = 0;
                curPid = AppHelper.getRunningProcessId(context, packageName);
//                MyTimberLog.d("wait curPid = %s", curPid);
                if(lastPid != 0 && lastPid != curPid){
                    // app restart?
//                    MyTimberLog.i("wait task finished!curPid = %s, lastPid = %s", curPid, lastPid);
                    AdbDevice.forceStopApp(Constants.TARGET_PACKAGE);
                    break;
                }
                lastPid = curPid;
            }
            Sleeper.sleep(1000);
        }
//        MyTimberLog.i("instrumentation finish.");
        AdbDevice.forceStopApp(packageName);
    }
}
