package com.zhongxing.elemeauto.page;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.llx278.uimocker2.Scroller;
import com.llx278.uimocker2.Solo;
import com.zhongxing.elemeauto.SoloUtils;
import com.ziguangcn.amazonsdk.Sleeper;


/**
 * Created by xulun on 2019/7/10.
 */

public class ShopActivity extends AppPage {
    private static final String TAG = "ShopActivity ";
    public ShopActivity(Solo solo) {
        super(solo);
    }

    public void checkLogin(){
        TextView loginButton = getSolo().getSearcher().searchTextViewByText("请登陆",false);
        if (loginButton != null){
            getSolo().getClicker().clickOnView(loginButton);
        }
    }

    public void findFoodTitle(String[] food){
        RecyclerView recyclerView = getSolo().getView(RecyclerView.class,0);
        int test = recyclerView.getItemDecorationCount();
        Log.d(TAG, "findFoodTitleCount: "+ test);
    }

    public View scrollToList(){
        View titleView = getSolo().getView("me.ele:id/banner_layout");
        View toolBar = getSolo().getView("me.ele:id/shadow");
        Log.d(TAG, "findFoodTitle: scroll to down");
        SoloUtils.getInstance(getSolo()).dragUp(titleView,toolBar);
        getSolo().sleep(3000);
        Log.d(TAG, "findFoodTitle: scroll to down 2");
        for (int i = 0; i < 3; ++i ) {
            View titleView1 = getSolo().getView("me.ele:id/tool_bar_bg");
            View head = getSolo().getView("me.ele:id/list");
            SoloUtils.getInstance(getSolo()).dragUp(head, titleView1);
            getSolo().sleep(3000);
            Log.d(TAG, "findFoodTitle: scroll to listView");
        }
        View recyclerView = getSolo().getView("me.ele:id/list_menu");
        if (recyclerView == null){
            Log.d(TAG, "findStoreTitle: list viev not found!");
            return null;
        }
        return recyclerView;
    }


    public boolean addFood(){
        View recyclerView = scrollToList();
        if (recyclerView == null){
            return false;
        }
        Log.d(TAG, "findFoodTitle: start find food");
        getSolo().getClicker().clickOnView(getSolo().getView("me.ele:id/name"));
        Sleeper.sleep(2*1000);
        Log.d(TAG, "findFoodTitle: scroll to list top");
        getSolo().getClicker().clickOnView(getSolo().getView("me.ele:id/add"));
        Sleeper.sleep(2*1000);
        getSolo().getClicker().clickOnText("去凑单");
        return true;
    }

    public void cd(){
        ListView listView =  getSolo().getView(ListView.class,0);
        int lastTotal = 0;
        int sameTotalTimes = 0;
        boolean firstLoaded = true;
        if (listView == null){
            Log.d(TAG, "cd: ListView not found");
        }
        while (true){
            if(lastTotal == listView.getCount()){
                sameTotalTimes += 1;
            }else{
                sameTotalTimes = 0;
            }
            lastTotal = listView.getCount();
        }
    }

    public LinearLayout findFirstProduct(ListView listView){
        int total = listView.getCount();
        for(int i = 0; i < total; ++i){
            View linearLayout = listView.getChildAt(i);
            if (isProduct(linearLayout)){
                return (LinearLayout)linearLayout;
            }
        }
        return null;
    }

    private boolean isProduct(View v){
        if (v instanceof LinearLayout){
            int total = ((LinearLayout)v).getChildCount();
            for(int i = 0; i < total; ++i){
                View childView = ((LinearLayout)v).getChildAt(i);
                if (childView instanceof FrameLayout){
                    return true;
                }
            }
        }
        return false;
    }


    public View findFoodAddButton(String foodName){
        ListView listView = getSolo().getView(ListView.class,0);
        int lastTotal = 0;
        int sameTotalTimes = 0;
        boolean firstLoaded = true;
        if(listView == null){
            Log.d(TAG, "findFoodAddButton: findProductTitle ListView not found!");
            return null;
        }
        while (true){
            if(lastTotal == listView.getCount()){
                sameTotalTimes += 1;
            }else {
                sameTotalTimes = 1;
            }
            if(firstLoaded){
                Log.d(TAG, "findFoodAddButton: findProductTitle scroll to top first.");
                getSolo().scrollToTop();
                firstLoaded = false;
            }
            lastTotal = listView.getCount();
            Log.d(TAG, "findFoodAddButton: findProductTitle lastTotal = "+ lastTotal);
            TextView productTitleView = getSolo().getSearcher().searchTextViewByTextWithVerticallyScroll(foodName, true, listView, Scroller.VerticalDirection.DOWN_TO_UP, 20000);
            if (productTitleView != null){
                LinearLayout linearLayout = (LinearLayout)productTitleView.getParent().getParent().getParent();
                FrameLayout addLayout  = (FrameLayout) linearLayout.getChildAt(linearLayout.getChildCount()-1);

            }
        }
    }

    public View findFoodTitle(String food){
        View recyclerView = scrollToList();
        if (recyclerView == null){
            return null;
        }
        Log.d(TAG, "findFoodTitle: start find food");
        getSolo().getClicker().clickOnView(getSolo().getView("me.ele:id/name"));
//        getSolo().scrollToTop();
        Sleeper.sleep(2*1000);
        Log.d(TAG, "findFoodTitle: scroll to list top");
        TextView foodView;
        while (true){
            foodView = getSolo().getSearcher()
                    .searchTextViewByTextWithVerticallyScroll(
                            food,
                            true,
                            recyclerView,
                            Scroller.VerticalDirection.DOWN_TO_UP,
                            20000);
            if (foodView != null){
                Log.d(TAG, "findFoodTitle: fined food");
                return foodView;
            }
            getSolo().sleep(2000);
//            getSolo().getClicker().clickOnView(foodViewGroup);
////                View foodName = getSolo().getView("me.ele:id/food_name");
////                View top = getSolo().getView("me.ele:id/top_space");
////                SoloUtils.getInstance(getSolo()).dragUp(foodName,top);
////                getSolo().getView(ImageView.class,1);
//            getSolo().getClicker().clickOnView(getSolo().getView(ImageView.class,1));
//            getSolo().sleep(4000);
//            View addView = getSolo().getView("me.ele:id/add",0);
//            getSolo().getClicker().clickOnView(addView);

        }
    }

    public void addFood(String foodName){
        addFood();
//        View foodView = findFoodTitle(foodName);
//        ViewGroup viewGroup = SoloUtils.getInstance(getSolo()).getFirstAncestorByClass(foodView,ViewGroup.class);
//        getSolo().getClicker().clickOnView(viewGroup);
//        Sleeper.sleep(2000);
//        View shareView = getSolo().getView("me.ele:id/share");
//        getSolo().getClicker().clickOnView(shareView);
//        Sleeper.sleep(2000);
//        getSolo().goBack();
//        Sleeper.sleep(2000);
//        View addButton = getSolo().getView("me.ele:id/add");
//        Log.d(TAG, "addFood: add view size = "+ getSolo().getViews(addButton).size());
//        int addButtonIndex = addButton.getVisibility();
//        getSolo().getClicker().clickOnView(addButton);
//        Log.d(TAG, "addFood: Index  = " + addButtonIndex);
    }
}
