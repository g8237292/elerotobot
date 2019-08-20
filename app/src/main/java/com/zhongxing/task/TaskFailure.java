package com.zhongxing.task;

import android.os.Message;

/**
 * Created by xulun on 2019/6/23.
 */

public class TaskFailure extends Exception {
    public TaskFailure(String message) {
        super(message);
    }
}
