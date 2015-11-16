package com.wsgmac1105.demo.entity;

/**
 * Created by wushaoge on 15/11/13.
 */
public class AnyEvent {

    public AnyEvent(){

    }

    public AnyEvent(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
