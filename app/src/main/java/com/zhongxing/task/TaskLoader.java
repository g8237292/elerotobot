package com.zhongxing.task;

import android.content.Context;
import android.util.Log;

import com.zhongxing.elemerobot.Constants;

import org.json.JSONException;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by xulun on 2019/6/22.
 */

public class TaskLoader {
    public static TaskGroup load(Context context, String SchedulerPackage, String filename){
        Log.e(TAG, "load: "+ SchedulerPackage+ filename );
        String taskStr = FileHelper.loadExternalFile(context.getApplicationContext(),
                SchedulerPackage, filename );
        Log.d(TAG, "load task :" + taskStr);

        try {
            return TaskGroup.parse(taskStr);
        } catch (JSONException e) {
            Log.e(TAG, "load: ",e );
            return  null;
        }
    }
}
