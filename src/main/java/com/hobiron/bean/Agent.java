package com.hobiron.bean;

public class Agent {

    private int id;
    private String ssHost;
    private int ssMinPort;
    private int ssMaxPort;
    private String ssEncryptMethod;
    private String baseUrl;
    private long maxFlowBytes;

    public Agent() {
    }

    public Agent(String ssHost, int ssMinPort, int ssMaxPort, String ssEncryptMethod, String baseUrl,
            long maxFlowBytes) {
        this.ssHost = ssHost;
        this.ssMinPort = ssMinPort;
        this.ssMaxPort = ssMaxPort;
        this.ssEncryptMethod = ssEncryptMethod;
        this.baseUrl = baseUrl;
        this.maxFlowBytes = maxFlowBytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsHost() {
        return ssHost;
    }

    public void setSsHost(String ssHost) {
        this.ssHost = ssHost;
    }

    public int getSsMinPort() {
        return ssMinPort;
    }

    public void setSsMinPort(int ssMinPort) {
        this.ssMinPort = ssMinPort;
    }

    public int getSsMaxPort() {
        return ssMaxPort;
    }

    public void setSsMaxPort(int ssMaxPort) {
        this.ssMaxPort = ssMaxPort;
    }

    public String getSsEncryptMethod() {
        return ssEncryptMethod;
    }

    public void setSsEncryptMethod(String ssEncryptMethod) {
        this.ssEncryptMethod = ssEncryptMethod;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getMaxFlowBytes() {
        return maxFlowBytes;
    }

    public void setMaxFlowBytes(long maxFlowBytes) {
        this.maxFlowBytes = maxFlowBytes;
    }

}
