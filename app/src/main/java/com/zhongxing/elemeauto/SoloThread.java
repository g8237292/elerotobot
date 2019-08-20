package com.zhongxing.elemeauto;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.llx278.uimocker2.Solo;
import com.zhongxing.elemeauto.page.ChangeAddressActivity;
import com.zhongxing.elemeauto.page.HomeActivity;
import com.zhongxing.elemeauto.page.SearchActivity;
import com.zhongxing.elemeauto.page.ShopActivity;
import com.zhongxing.elemeauto.page.UIController;
import com.zhongxing.elemerobot.Constants;
import com.zhongxing.task.BaseTask;
import com.zhongxing.task.BuyTask;
import com.zhongxing.task.TaskFailure;
import com.zhongxing.task.TaskGroup;
import com.zhongxing.task.TaskLoader;
import com.zhongxing.task.TaskSuccess;
import com.ziguangcn.amazonsdk.Sleeper;

import java.util.function.ToDoubleBiFunction;

import xdroid.toaster.Toaster;

/**
 * Created by xulun on 2019/6/11.
 */

public class SoloThread implements Runnable {
    private static final String TAG = "SoloThread";
    private static final SoloThread ourInstance = new SoloThread();
    private Solo mSolo;

    private SoloThread(){
        mSolo = null;
    }


    public static SoloThread getInstance(){
        return ourInstance;
    }

    public SoloThread setSolo(Solo solo){
        mSolo = solo;
        return this;
    }

    public Solo getSolo(){
        return mSolo;
    }

    private void exitProcess(Context context){
        mSolo.getActivityUtils().finishOpenedActivities();
        System.exit(1);
        // su调用会被FakeDeviceData RootCloak模块阻止
//        AdbDevice.forceStopApp(context.getPackageName());
    }

    private void doTask(TaskGroup taskGroup, int curTaskIndex) throws TaskSuccess, TaskFailure {
        BaseTask curTask = taskGroup.get(curTaskIndex);

        while (true){
            if(curTask.isTimeout()){
                throw new TaskFailure("task Timeout");
            }

            if(onActivity(taskGroup, curTaskIndex)){
                Sleeper.sleep(100);
                continue;
            }
            Sleeper.sleep(100);
            break;
        }

    }
    @Override
    public void run() {
        UIController.getInstance().setSolo(mSolo);
        Log.d(TAG, "run: start auto");
        UIController.getInstance().setSolo(mSolo);
        TaskGroup taskGroup;
        taskGroup = TaskLoader.load(mSolo.getContext(), Constants.SELF_FILES_PACKAGE, Constants.TASK_FILENAME);
        if(taskGroup == null){
            Log.e(TAG, "run: Load task failed" );
            Toaster.toastLong("Load task failed");
            exitProcess(mSolo.getContext());
            return;
        }

        if(taskGroup.count() == 0){
            Log.e(TAG, "run: task not found in +" +taskGroup.toString() );
            Toaster.toastLong("未找到任务");
            exitProcess(mSolo.getContext());
        }
        for(int i=0;i < taskGroup.count(); ++i){
            BaseTask task = taskGroup.get(i);
            task.setTimeout(Constants.TIME_OUT);
            while (true){
                if(task.isTimeout()){
                    Log.e(TAG, "run Time out : "+ task.toString() );
                    Toaster.toastLong("任务超时");
                    break;
                }
                try {
                    doTask(taskGroup, i);
                }catch (TaskSuccess taskSuccess){
                    Log.i(TAG, "run: Task Success");
                }catch (TaskFailure taskFailure){
                    Log.i(TAG, "run: Task Failed");
                }
            }
        }

    }

    private boolean onActivity(TaskGroup taskGroup, int curTaskIndex) throws TaskFailure, TaskSuccess{
        BaseTask task = taskGroup.get(curTaskIndex);
        Sleeper.sleep(2000);
        Activity curActicity = mSolo.getActivityUtils().getCurrentActivity();
        if(curActicity==null){
            return true;
        }
        String curActivityName = mSolo.getActivityUtils().getCurrentActivity().getClass().getSimpleName();
        Log.d(TAG, "onActivity curActivity: " + curActivityName);
        switch (curActivityName){
            case "HomeActivity":
                Log.d(TAG, "onActivity: OnHomepage");
                HomeActivity homeActivity = UIController.getInstance().getHomeActivity();
                homeActivity.search("小面");
            case "ChangeAddressActivity":
                ChangeAddressActivity changeAddressActivity = UIController.getInstance().getChangeAddressActivity();
                changeAddressActivity.clickManager();
            case "SearchActivity":
                SearchActivity searchActivity = UIController.getInstance().getsearchActivity();
                TextView storeTitleView = searchActivity.findStoreTitle("123");
                if (storeTitleView != null){
                    ViewGroup storeView  = SoloUtils.getInstance(mSolo).getFirstAncestorByClass(storeTitleView,ViewGroup.class);
                    if (storeView != null){
                        mSolo.getClicker().clickOnView(storeView);
                    }
                }
// else{
//                    // TODO: 2019/7/12  没找到产品
//                }
            case "ShopActivity":
                ShopActivity shopActivity = UIController.getInstance().getShopActivity();
                shopActivity.addFood("煎蛋");
            case "LoginActivity":
            case "LoginByUsernameActivity":


        }
        return false;
    }


}
