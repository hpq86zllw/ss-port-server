package com.hobiron.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class Command {

    private String agentToken;
    private String content;

    private static final Gson GSON = new Gson();

    public Command(String agentToken, String content) {
        this.agentToken = agentToken;
        this.content = content;
    }

    public String getAgentToken() {
        return agentToken;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

    @SuppressWarnings("unchecked")
    public AgentFlow convertToAgentFlow() {

        String statToken = "stat:";
        if (!this.content.startsWith(statToken)) {
            throw new IllegalArgumentException("Content does not start with 'stat:'");
        }

        Map<String, Object> flowMap = GSON.fromJson(this.content.replace(statToken, ""), Map.class);

        Map<Integer, Long> portBytesMap = new HashMap<>();
        Set<String> flowMapKeySet = flowMap.keySet();
        for (String flowMapKey : flowMapKeySet) {
            Integer portNo = Integer.parseInt(flowMapKey);
            Long bytes = ((Double) flowMap.get(flowMapKey)).longValue();
            portBytesMap.put(portNo, bytes);
        }

        return new AgentFlow(agentToken, portBytesMap);

    }

}
