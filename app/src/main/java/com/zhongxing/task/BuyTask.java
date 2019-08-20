package com.zhongxing.task;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by xulun on 2019/6/13.
 */
//[{'id':1,'searchKeyword':'testkeyword','store':'teststore','goods':['testgoods','testgoods2'],'goodsPrice':['11.11','22.22'],'buyPrice':'22.22','storePhone':'88-32323','remarks':'test'}]
public class BuyTask extends BaseTask{
    private static volatile BuyTask instance = null;
    private String searchKeyword;
    private String store;
    private String[] goods;
    private Float[] goodsPrice;
    private String buyPrice;
    private String storePhone;
    private String remarks;

    public BuyTask(){
        super();
    }

    public BuyTask(JSONObject taskJson) throws JSONException {
        super(taskJson);
        Gson gson = new Gson();
        this.searchKeyword = taskJson.getString("searchKeyword");
        this.store = taskJson.getString("store");
        String goodsStr = taskJson.getJSONArray("goods").toString();
        String goodsPriceStr = taskJson.getJSONArray("goodsPrice").toString();
        this.goods = gson.fromJson(goodsStr, String[].class);
        this.goodsPrice = gson.fromJson(goodsPriceStr, Float[].class);
        this.buyPrice = taskJson.getString("buyPrice");
        this.storePhone = taskJson.getString("storePhone");
        this.remarks = taskJson.getString("remarks");
    }



    public static BuyTask getInstance(){
        synchronized (BuyTask.class){
            if (instance == null){
                instance = new BuyTask();
            }
        }
        return instance;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String[] getGoods() {
        return goods;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
    }

    public Float[] getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float[] goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
