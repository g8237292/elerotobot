package com.zhongxing.elemeauto;

import android.app.AndroidAppHelper;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.llx278.uimocker2.AccessibilityManagerHook;
import com.llx278.uimocker2.InstrumentationDecorator;
import com.llx278.uimocker2.Solo;
import com.zhongxing.elemeauto.hook.ImageEntityHook;
import com.zhongxing.elemerobot.Constants;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * Created by xulun on 2019/6/6.
 */

public class ElemeAuto implements IXposedHookLoadPackage {
    private static final String TAG = "ElemeAuto";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (ProcessUtils.isSystemProcess()){
            XposedBridge.log("系统APP:" + loadPackageParam.packageName +
                    " uid:" + android.os.Process.myUid() +  "不勾");
            return;
        }else{
            XposedBridge.log("普通APP:" + loadPackageParam.packageName +
                    " uid:" + android.os.Process.myUid());
        }
//        检查钩子生效
        if(loadPackageParam.packageName.equalsIgnoreCase(Constants.SELF_PACKAGE)){

            XposedBridge.log("hook self success");
            XposedHelpers.findAndHookMethod(Constants.SELF_CLASS_NAME, loadPackageParam.classLoader,
                    "checkHookIsEffect", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            param.setResult(true);
                        }
                    });
        }
        if(loadPackageParam.packageName.equalsIgnoreCase(Constants.TARGET_PACKAGE)){
            Log.d(TAG, "handleLoadPackage: hook self success");
            XposedBridge.log("Hook eleme success");


            XposedHelpers.findAndHookMethod("me.ele.ApplicationContext",
                    loadPackageParam.classLoader, "onMainProcessCreate",new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            Context appContext = AndroidAppHelper.currentApplication().getApplicationContext();
                            ClassLoader cl = appContext.getClassLoader();
                            XposedBridge.log("Hook eleme method success");

                            new ImageEntityHook().hook(cl);
//                            Context appContext = AndroidAppHelper.currentApplication().getApplicationContext();
                            final Solo solo = new Solo(appContext);
                            new Thread(SoloThread.getInstance().setSolo(solo)).start();
                        }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                            Context appContext = AndroidAppHelper.currentApplication().getApplicationContext();
//                            XposedBridge.log("Hook eleme method success");
//                            final Solo solo = new Solo(appContext);
//                            new Thread(SoloThread.getInstance().setSolo(solo)).start();
//                        }
                    });
//            Context appContext = AndroidAppHelper.currentApplication().getApplicationContext();
//            final Solo solo = new Solo(appContext);
//            new Thread(SoloThread.getInstance().setSolo(solo)).start();
            new AccessibilityManagerHook().setOnAccessibilityEventListener(loadPackageParam,new MyOnAccessibilityEventListener());

        }
    }
}
