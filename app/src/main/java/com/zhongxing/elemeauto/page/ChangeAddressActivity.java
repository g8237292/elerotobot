package com.zhongxing.elemeauto.page;

import android.widget.TextView;

import com.llx278.uimocker2.Solo;

/**
 * Created by xulun on 2019/7/9.
 */

public class ChangeAddressActivity extends AppPage {

    private static final String managerButton = "me.ele:id/manager_text";

    public ChangeAddressActivity(Solo solo) {
        super(solo);
    }

    public void clickManager(){
        TextView manager = (TextView) getSolo().getView(managerButton);
        getSolo().getClicker().clickOnView(manager);
    }


}
