package com.zhongxing.elemeauto.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by xulun on 2019/6/24.
 */

public class ImageEntityHook {
    private static final String CLASS_TO_HOOK = "me.ele.service.shopping.model.CartShare";
    public boolean hook(ClassLoader cl) {
        Class<?> hookclass;
        try {
            hookclass = cl.loadClass(CLASS_TO_HOOK);
        }catch (Exception e){
            XposedBridge.log("HOOK " +  CLASS_TO_HOOK + "\n error" +e.getMessage());
            return false;
        }
        XposedBridge.log("Hook "+CLASS_TO_HOOK +" success");

        XposedHelpers.findAndHookMethod(hookclass, "getImagePath", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String url = (String) param.getResult();
                XposedBridge.log("Hook image URL = " +url);
                param.setResult("");
            }

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String url = (String) param.getResult();
                XposedBridge.log("Hook image URL = " +url);
                param.setResult("");
            }
        });
        return true;
    }

}
