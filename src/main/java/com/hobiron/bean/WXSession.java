package com.hobiron.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class WXSession {

    private String openid;
    @SerializedName("session_key")
    private String sessionKey;
    private String unionid;

    private static final Gson GSON = new Gson();

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
