package com.zhongxing.elemeauto;

/**
 * Created by xulun on 2019/6/6.
 */

public class ProcessUtils {
    public static boolean isSystemProcess(){
        return android.os.Process.myUid() <= 10000;
    }
}
