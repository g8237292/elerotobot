package com.zhongxing.task;

/**
 * Created by xulun on 2019/6/23.
 */

public class TaskSuccess extends Exception {
    private String oid;
    private String orderPrice;

    public TaskSuccess(String message, String oid, String orderPrice){
        super(message);
        this.oid = oid;
        this.orderPrice = orderPrice;
    }

    TaskSuccess(){
        super("");
        this.oid = "";
        this.orderPrice = "";
    }

    TaskSuccess(String message){
        super(message);
        this.oid = "";
        this.orderPrice = "";
    }

    public String getOid() {
        return oid;
    }

    public String getOrderPrice() {
        return orderPrice;
    }
}
