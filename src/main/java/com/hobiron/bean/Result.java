package com.hobiron.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

public class Result<T> {

    private int status;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private static final Gson GSON = new Gson();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> newSuccess(String msg) {
        Result<T> result = new Result<>();
        result.status = 1;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> newSuccess(String msg, T data) {
        Result<T> result = new Result<>();
        result.status = 1;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> Result<T> newFail(String msg) {
        Result<T> result = new Result<>();
        result.status = 0;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> newError(String msg) {
        Result<T> result = new Result<>();
        result.status = -99;
        result.msg = msg;
        return result;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
