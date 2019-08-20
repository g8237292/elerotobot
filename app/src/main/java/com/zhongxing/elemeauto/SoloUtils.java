package com.zhongxing.elemeauto;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import com.llx278.uimocker2.Solo;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.AssertionFailedError;

/**
 * Created by xulun on 2019/7/9.
 */

public class SoloUtils {

    private static SoloUtils mInstance = null;
    private Solo solo;
    private static final String TAG = SoloUtils.class.getSimpleName();

    private SoloUtils(Solo solo){this.solo = solo;}

    public static synchronized SoloUtils getInstance(Solo solo) {
        if (mInstance == null) {
            mInstance = new SoloUtils(solo);
        }
        return mInstance;
    }

    public boolean clickOnView(String viewId){
        try {
            View view = solo.getView(viewId);
            if(view.getVisibility() == View.VISIBLE){
                solo.getClicker().clickOnView(view);
                return true;
            }
            Log.d(TAG, "clickOnView: View invisible or gone");
            return false;
        }catch (AssertionFailedError ex){
            Log.e(TAG, "clickOnView: ",ex );
            return false;
        }

    }

    public boolean clickById(String byId) {
        if (byId.isEmpty()){
            return false;
        }
        try {
            Activity activity = solo.getActivityUtils().getCurrentActivity();
            int id = activity.getResources().getIdentifier(byId,"id", activity.getPackageName());
            View view = solo.findViewById(id);
            solo.getClicker().clickOnView(view);
        }catch (Exception ex){
            Log.e(TAG, "clickById: ",ex );
            return false;
        }

        return true;
    }

    public void dragUp(View botton,View top){
        int[] bottonPos = new int[2];
        int[] topPos = new int[2];
        int width = botton.getWidth();
        botton.getLocationOnScreen(bottonPos);
        top.getLocationOnScreen(topPos);
        solo.drag(bottonPos[0] +width /2,bottonPos[0]+width /2,bottonPos[1],topPos[1],2);
    }

    public <T extends View> T getFirstAncestorByClass(View view, Class<T> viewClass){
        if(view.getClass() == viewClass || viewClass.isAssignableFrom(view.getClass())){
            return viewClass.cast(view);
        }
        for(;;){
            ViewParent parent = view.getParent();
            if(parent == null){
                return null;
            }
            if(parent instanceof View){
                view = (View)parent;
                if(view.getClass() == viewClass || viewClass.isAssignableFrom(view.getClass())){
                    return viewClass.cast(view);
                }
            }
        }
    }
}
