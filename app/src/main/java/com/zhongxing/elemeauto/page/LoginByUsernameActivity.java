package com.zhongxing.elemeauto.page;

import android.view.View;
import android.widget.EditText;

import com.llx278.uimocker2.Solo;
import com.ziguangcn.amazonsdk.Sleeper;

/**
 * Created by xulun on 2019/7/10.
 */

public class LoginByUsernameActivity extends AppPage {
    public LoginByUsernameActivity(Solo solo) {
        super(solo);
    }

    public void login(String user, String pw){
        EditText username = (EditText) getSolo().getView("me.ele:id/easy_edit_text");
        EditText password = (EditText) getSolo().getView("me.ele:id/easy_edit_text",1);
        getSolo().clearEditText(username);
        getSolo().enterText(username, user);
        Sleeper.sleep(2000);
        getSolo().enterText(password, pw);
        Sleeper.sleep(2000);
        View loginButton = getSolo().getView("me.ele:id/login_submit");
        getSolo().getClicker().clickOnView(loginButton);
    }
}
