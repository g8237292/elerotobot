package com.zhongxing.elemeauto.page;

import com.llx278.uimocker2.Solo;

/**
 * Created by xulun on 2019/6/26.
 */

public class AppPage {
    private Solo solo;
    private com.robotium.solo.Solo newsolo;

    public AppPage(Solo solo){
        this.solo = solo;
    }

    public Solo getSolo(){
        return this.solo;
    }

    public com.robotium.solo.Solo getNewSoll(){
        return this.newsolo;
    }
}
