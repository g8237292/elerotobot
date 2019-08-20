package com.zhongxing.elemeauto.page;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.llx278.uimocker2.Solo;
import com.ziguangcn.amazonsdk.Sleeper;

import junit.framework.AssertionFailedError;

import xdroid.toaster.Toaster;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by xulun on 2019/6/26.
 */

public class HomeActivity extends AppPage{
    private static final String LocationButton = "me.ele:id/address_text";
    private static final String Searchicon = "me.ele:id/icon";

    public HomeActivity(Solo solo) {
        super(solo);
    }


    public void clickLocationButton (){
        try {
            TextView location = (TextView) getSolo().getView(LocationButton);
            getSolo().getClicker().clickOnView(location);

        }catch (AssertionFailedError e){
            Toaster.toast("failse");
        }
    }

    public boolean search(String keyword){
        Log.d(TAG, "searching: " + keyword);
        try {
            getSolo().sleep(15000);
//            第一个icon是定位 所以点第二个icon search
            View searchIcon  = getSolo().getView(Searchicon,1);
//            getSolo()getSolo.enterText(keywordEdit,keyword);
            Log.d(TAG, "search: clicking search input");
            getSolo().getClicker().clickOnView(searchIcon);
            Sleeper.sleep(3000);
            EditText keywordEdit = (EditText) getSolo().getView("me.ele:id/editor");
            getSolo().enterText(keywordEdit,keyword);
            getSolo().pressSoftKeyboardGoButton();
            getSolo().hideSoftKeyboard();
            return true;
        }catch (AssertionFailedError e){
            Toaster.toast("search excepition");
            return false;
        }
    }
}
