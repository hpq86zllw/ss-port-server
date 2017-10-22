package com.hobiron.bean;

import java.util.HashMap;
import java.util.Map;

public class AgentFlow {

    private String token;
    private Map<Integer, Long> portBytesMap = new HashMap<>();

    public AgentFlow(String token, Map<Integer, Long> portBytesMap) {
        this.token = token;
        this.portBytesMap = portBytesMap;
    }

    public String getToken() {
        return token;
    }

    public Map<Integer, Long> getPortBytesMap() {
        return portBytesMap;
    }

}
