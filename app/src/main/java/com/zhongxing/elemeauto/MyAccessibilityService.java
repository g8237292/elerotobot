package com.zhongxing.elemeauto;

import android.accessibilityservice.AccessibilityService;
import android.support.annotation.Nullable;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;




import java.util.List;

/**
 * Created by xulun on 2019/7/25.
 */

public class MyAccessibilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }
    /**
     * 根据getRootInActiveWindow查找当前id的控件
     *
     * @param idfullName id全称:com.android.xxx:id/tv_main
     */
    @Nullable
    public AccessibilityNodeInfo findViewById(String idfullName) {
        List<AccessibilityNodeInfo> list = findViewByIdList(idfullName);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<AccessibilityNodeInfo> findViewByIdList(String idfullName) {
        try {
            AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
            if (rootInfo == null) return null;
            List<AccessibilityNodeInfo> list = rootInfo.findAccessibilityNodeInfosByViewId(idfullName);
            rootInfo.recycle();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
