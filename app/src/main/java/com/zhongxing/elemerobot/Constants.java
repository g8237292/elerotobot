package com.zhongxing.elemerobot;

/**
 * Created by xulun on 2019/6/4.
 */

public  class Constants {
    public static final String TARGET_PACKAGE = BuildConfig.PACKAGENAME;

    public static final int TIME_OUT = 10 * 60 * 1000;

    public static final String QUEUE_NAME = BuildConfig.QUEUENAME;

    public static final String SELF_CLASS_NAME = "com.zhongxing.elemerobot.MainActivity";

    public static final String SELF_PACKAGE  = "com.zhongxing.elemerobot";

    public static final String SELF_FILES_PACKAGE  = "com.zhongxing.elemerobot.eleme";

    public static final String Access_Key_Id =  "AKIA33HZUNX5AQM3TVEJ";

    public static final String Access_Key_Secret = "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";

    public static final String TASK_FILENAME = "task.txt";
    public static String getQueueUrl(){
        return BuildConfig.QUEUEURL;
    }


}
