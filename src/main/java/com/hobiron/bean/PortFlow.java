package com.hobiron.bean;

import com.google.gson.Gson;

public class PortFlow {

    private String token;
    private int portNo;
    private long bytes;

    private static final Gson GSON = new Gson();
    
    public PortFlow(String token, int portNo, long bytes) {
        this.token = token;
        this.portNo = portNo;
        this.bytes = bytes;
    }

    public String getToken() {
        return token;
    }

    public int getPortNo() {
        return portNo;
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
