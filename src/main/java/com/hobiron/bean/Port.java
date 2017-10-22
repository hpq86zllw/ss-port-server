package com.hobiron.bean;

import com.google.gson.Gson;

public class Port {

    private long usedFlowBytes;
    private long totalFlowBytes;
    private String ssHost;
    private int portNo;
    private String password;
    private String ssEncryptMethod;

    private static final Gson GSON = new Gson();

    public long getUsedFlowBytes() {
        return usedFlowBytes;
    }

    public void setUsedFlowBytes(long usedFlowBytes) {
        this.usedFlowBytes = usedFlowBytes;
    }

    public long getTotalFlowBytes() {
        return totalFlowBytes;
    }

    public void setTotalFlowBytes(long totalFlowBytes) {
        this.totalFlowBytes = totalFlowBytes;
    }

    public String getSsHost() {
        return ssHost;
    }

    public void setSsHost(String ssHost) {
        this.ssHost = ssHost;
    }

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsEncryptMethod() {
        return ssEncryptMethod;
    }

    public void setSsEncryptMethod(String ssEncryptMethod) {
        this.ssEncryptMethod = ssEncryptMethod;
    }

    public String getFormattedUsedFlow() {
        return String.format("%.2f GB", (double) this.usedFlowBytes / 1024 / 1024 / 1024);
    }

    public String getFormattedTotalFlow() {
        return String.format("%.2f GB", (double) this.totalFlowBytes / 1024 / 1024 / 1024);
    }

    public double getUsedFlowPercent() {
        return (double) this.usedFlowBytes / this.totalFlowBytes * 100;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
