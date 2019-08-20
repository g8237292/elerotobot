package com.zhongxing.elemeauto.page;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.llx278.uimocker2.Scroller;
import com.llx278.uimocker2.Solo;
import com.zhongxing.elemeauto.SoloUtils;


/**
 * Created by xulun on 2019/7/9.
 * 搜索结果页面
 */

public class SearchActivity extends AppPage {
    private static final String TAG = "SearchActivity";

    public SearchActivity(Solo solo) {
        super(solo);
    }

    public TextView findStoreTitle(String title){
        getSolo().sleep(2000);
        getSolo().scrollDown();
        Log.d(TAG, "findStoreTitle: down");
        getSolo().sleep(2000);
        getSolo().scrollToTop();
        Log.d(TAG, "findStoreTitle: top");
//        RecyclerView recyclerView = getSolo().getView(RecyclerView.class,0);
        View recyclerView = getSolo().getView("me.ele:id/id_recyclerview");

//        ViewGroup recyclerView1= (ViewGroup) recyclerView;
//        recyclerView1.findViewById(1);
//        int lastTotal = 0;
//        int sameTotalTimes = 0;
//        boolean firstLoaded = true;
        if (recyclerView == null){
            Log.d(TAG, "findStoreTitle: list viev not found!");
            return null;
        }
        while (true){
            TextView storeView = getSolo().getSearcher()
                    .searchTextViewByTextWithVerticallyScroll(
                            "正宗重庆酸辣粉",
                            true,
                            recyclerView,
                            Scroller.VerticalDirection.DOWN_TO_UP,
                            20000);
            if(storeView != null){
                return storeView;
            }
            getSolo().sleep(2000);

        }

    }

}
