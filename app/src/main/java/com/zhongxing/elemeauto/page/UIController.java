package com.zhongxing.elemeauto.page;

import com.llx278.uimocker2.Solo;

/**
 * Created by xulun on 2019/6/26.
 */

public class UIController {
    private static final UIController ourInstance = new UIController();
    private Solo mSolo;

    private HomeActivity homeActivity;
    private ChangeAddressActivity changeAddressActivity;
    private SearchActivity searchActivity;
    private ShopActivity shopActivity;
    private LoginActivity loginActivity;
    private LoginByUsernameActivity loginByUsernameActivity;

    public static UIController getInstance(){
        return ourInstance;
    }

    private UIController(){}

    public void setSolo(Solo solo){this.mSolo = solo;}

    public HomeActivity getHomeActivity(){
        if(homeActivity == null){
            homeActivity = new HomeActivity(mSolo);
        }
        return homeActivity;
    }

    public ChangeAddressActivity getChangeAddressActivity(){
        if (changeAddressActivity == null){
            changeAddressActivity = new ChangeAddressActivity(mSolo);
        }
        return changeAddressActivity;
    }

    public SearchActivity getsearchActivity(){
        if (searchActivity == null){
            searchActivity = new SearchActivity(mSolo);
        }
        return searchActivity;
    }

    public ShopActivity getShopActivity(){
        if (shopActivity == null){
            shopActivity = new ShopActivity(mSolo);
        }
        return shopActivity;
    }

    public LoginActivity getLoginActivity() {
        if (loginActivity == null){
            loginActivity = new LoginActivity(mSolo);
        }
        return loginActivity;
    }

    public LoginByUsernameActivity getLoginByUsernameActivity() {
        if (loginByUsernameActivity == null){
            loginByUsernameActivity = new LoginByUsernameActivity(mSolo);
        }
        return loginByUsernameActivity;
    }
}
