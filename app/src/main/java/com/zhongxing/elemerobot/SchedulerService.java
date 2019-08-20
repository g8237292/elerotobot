package com.zhongxing.elemerobot;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.zhongxing.task.FileHelper;
import com.zhongxing.task.TaskGroup;
import com.zhongxing.task.TaskService;

import java.io.FileInputStream;


/**
 * Created by xulun on 2019/6/5.
 */

public class SchedulerService extends IntentService {
    private static final String TAG = "SchedulerService";

    public SchedulerService(){this(SchedulerService.class.getName());}

    public SchedulerService(String name) {
        super(name);
    }

    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate: start service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ok");
        TaskGroup task = TaskService.getInstance().getTask();
        if (task==null){
            Log.e(TAG, "onHandleIntent: no task found");
        }
        FileHelper.saveTextFile(this, Constants.TASK_FILENAME,task.toString());
        AppRunner.run(this,Constants.TARGET_PACKAGE);
    }
}
