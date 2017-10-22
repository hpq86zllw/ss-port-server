package com.hobiron.tx.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobiron.bean.Agent;
import com.hobiron.dao.AgentDao;
import com.hobiron.dao.PortDao;
import com.hobiron.tx.AgentService;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentDao agentDao;
    @Autowired
    private PortDao portDao;

    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

    @Override
    public String authorize(Agent agent) {

        String token = UUID.randomUUID().toString();
        int resultNum = agentDao.updateAgentBySsHost(agent.getSsHost(), agent.getSsEncryptMethod(), agent.getBaseUrl(),
                token);
        if (resultNum == 1) {
            logger.info("Update agent {} with token {}", agent.getSsHost(), token);
            return token;
        }
        logger.info("New agent {} with token {}", agent.getSsHost(), token);

        agentDao.insertAgent(agent.getSsHost(), agent.getSsEncryptMethod(), agent.getBaseUrl(), token);
        Agent savedAgent = agentDao.selectAgentBySsHost(agent.getSsHost());
        for (int port = agent.getSsMinPort(); port <= agent.getSsMaxPort(); port++) {
            portDao.insertPort(savedAgent.getId(), agent.getMaxFlowBytes(), port);
        }

        return token;

    }

}
