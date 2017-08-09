package com.example.penny.accessibilityyysp.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by penny on 2016/9/14 0014.
 *
 */
public class BaseResponse<T> implements Serializable {


    @SerializedName("bizCode")
    public int bizCode;

    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public T data;

    @SerializedName("msg")
    public String msg;

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
//        notifyChange(BR.bizCode);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
//        notifyChange(BR.code);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
//        notifyChange(BR.data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
//        notifyChange(BR.msg);
    }

}

