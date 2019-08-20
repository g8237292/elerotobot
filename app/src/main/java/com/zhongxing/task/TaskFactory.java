package com.zhongxing.task;

import android.os.Build;
import android.util.Log;

import com.zhongxing.elemerobot.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by xulun on 2019/6/13.
 */

public class TaskFactory {
    private static final String TAG = "TaskFactory";

    public static BaseTask create(JSONObject taskJson) throws JSONException{
        switch (BuildConfig.FEATURE){
            case "BUY":
                return createBuyTask(taskJson);
            case "COMMENT":
//                return createCommentTask(taskJson);
            default:
                return null;
        }
    }

    private static BuyTask createBuyTask(JSONObject taskJson) throws JSONException{
        Log.d(TAG, "createBuyTask: ");
        return new BuyTask(taskJson);
    }
}
