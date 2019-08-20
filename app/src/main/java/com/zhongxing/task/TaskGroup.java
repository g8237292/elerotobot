package com.zhongxing.task;

import android.util.Log;

import com.ziguangcn.amazonsdk.Sleeper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xulun on 2019/6/13.
 */

//"[{'id':1},{'id':2}]"

public class TaskGroup {
    private static final String TAG = "TaskGroup";
    private List<BaseTask> tasks;
    private boolean  addCart;
    private int addCartSuccessCount;
    private int taskCount;
    private String org;

    public TaskGroup(){
        this.tasks = new ArrayList<>();
        this.addCart = false;
        this.addCartSuccessCount = 0;
        this.taskCount = 0;
        this.org = null;
    }

    public static TaskGroup parse(String taskGroupStr) throws JSONException {
        Log.d(TAG, "parse: load task = " +taskGroupStr);
        JSONArray tasks = new JSONArray(taskGroupStr);
        TaskGroup group = new TaskGroup();
        group.setOrg(taskGroupStr);
        for(int i=0; i<tasks.length(); ++i)  {
            JSONObject singleTask = tasks.getJSONObject(i);
            BaseTask task = TaskFactory.create(singleTask);
            if(task==null){
                continue;
            }
            group.add(task);
        }
        return group;
    }

    public String toString() {
        return this.org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public boolean add(BaseTask task){
        return this.tasks.add(task);
    }

    public int count(){
        return this.tasks.size();
    }

    public BaseTask get(int i){
        return this.tasks.get(i);
    }
}
