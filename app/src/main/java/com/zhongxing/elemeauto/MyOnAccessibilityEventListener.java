package com.zhongxing.elemeauto;

import android.app.UiAutomation;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

import com.llx278.uimocker2.Solo;
import com.ziguangcn.amazonsdk.Sleeper;

import java.util.List;

/**
 * Created by xulun on 2019/7/22.
 */

public class MyOnAccessibilityEventListener implements UiAutomation.OnAccessibilityEventListener {
    private static final String TAG = "MyOnAccessibilityEventL";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.d(TAG, "onAccessibilityEvent: "  + accessibilityEvent.toString());
        if(accessibilityEvent.getClassName().equals("com.afollestad.materialdialogs.MaterialDialog")){
            if (eventContainsText(accessibilityEvent,"新版本已准备好")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Sleeper.sleep(2000);
                        Solo solo = SoloThread.getInstance().getSolo();
                        Button noButton = (Button) solo.getView("me.ele:id/buttonDefaultNegative");
                        solo.getClicker().clickOnView(noButton);
                        Log.d(TAG, "run: diss miss upgrade");
                    }
                }).start();
            }
        }
        if (accessibilityEvent.getClassName().equals("me.ele.booking.ui.checkout.dialog.a")){
            if (eventContainsText(accessibilityEvent,"去加购")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Sleeper.sleep(2000);
                        Solo solo = SoloThread.getInstance().getSolo();
                        View closeView = solo.getView("me.ele:id/btn_close");
                        solo.getClicker().clickOnView(closeView);
                        Log.d(TAG, "run: diss miss upgrade");
                    }
                }).start();
            }
        }
    }


    private static boolean eventContainsText(AccessibilityEvent event, String text){
        for (CharSequence curText: event.getText()){
            if (curText.toString().contains(text)){
                return true;
            }
        }
        return false;
    }



}
