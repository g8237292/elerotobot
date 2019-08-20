package com.zhongxing.elemeauto.page;

import com.llx278.uimocker2.Solo;

/**
 * Created by xulun on 2019/7/10.
 */

public class LoginActivity extends AppPage {
    public LoginActivity(Solo solo) {
        super(solo);
    }

    public void goPasswordLogin(){
        getSolo().getClicker().clickOnText("密码登陆");
    }
}
