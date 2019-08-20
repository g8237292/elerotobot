package com.zhongxing.task;

import android.os.SystemClock;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xulun on 2019/6/13.
 */

public class BaseTask {
    private long endTime;

    public void setTimeout(int timeout) {
        this.endTime = SystemClock.uptimeMillis() + (long)timeout;
    }

    public boolean isTimeout(){
        return (SystemClock.uptimeMillis() >this.endTime);
    }

    enum TaskType{
        /**
         *  购买任务
         */
        Buy,
        /**
         *  评论任务
         */
        Comment

    }
    private int id;

    public BaseTask() {}
    public BaseTask(JSONObject taskJson) throws JSONException {
        this.id = taskJson.getInt("id");

    }

}
