package com.hobiron.dao;

import com.hobiron.bean.Agent;

public interface AgentDao {

    int updateAgentBySsHost(String ssHost, String ssEncryptMethod, String baseUrl, String token);

    void insertAgent(String ssHost, String ssEncryptMethod, String baseUrl, String token);

    Agent selectAgentBySsHost(String ssHost);

}
