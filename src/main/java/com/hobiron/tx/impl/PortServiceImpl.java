package com.hobiron.tx.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hobiron.bean.Agent;
import com.hobiron.bean.Port;
import com.hobiron.bean.PortFlow;
import com.hobiron.bean.Result;
import com.hobiron.bean.User;
import com.hobiron.dao.AgentDao;
import com.hobiron.dao.PortDao;
import com.hobiron.dao.UserDao;
import com.hobiron.exception.NoAvailablePortExcpetion;
import com.hobiron.http.HttpFactory;
import com.hobiron.tx.PortService;

@Service
public class PortServiceImpl implements PortService {

    @Autowired
    private PortDao portDao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpFactory httpFactory;

    private static final Gson GSON = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(PortServiceImpl.class);

    @Override
    public Port getPort(String openid) {
        return portDao.selectPortByOpenid(openid);
    }

    @Override
    public void applyPort(String openid) {

        User user = userDao.selectUserByOpenid(openid);
        int resultNum = portDao.updateAvailablePortToAssigned(user.getId(), RandomStringUtils.randomAlphanumeric(7));
        if (resultNum != 1) {
            throw new NoAvailablePortExcpetion("No port to apply");
        }

        Port port = getPort(openid);
        enablePort(port);

    }

    @Override
    public void deletePort(String openid) {

        Port port = getPort(openid);
        disablePort(port);

        User user = userDao.selectUserByOpenid(openid);
        portDao.updateAssignedPortToAvailable(user.getId());

    }

    @Override
    public void calculatePortFlow(PortFlow portFlow) {
        int resultNum = portDao.increatePortUsedFlowBytes(portFlow.getPortNo(), portFlow.getToken(),
                portFlow.getBytes());
        if (resultNum != 1) {
            throw new NoAvailablePortExcpetion("No port to calculate");
        }
    }

    @Override
    public void enablePort(Port port) {

        Agent agent = agentDao.selectAgentBySsHost(port.getSsHost());

        Map<String, Object> portMap = new HashMap<>();
        portMap.put("server_port", port.getPortNo());
        portMap.put("password", port.getPassword());

        Result<Map<String, Object>> result = httpFactory.createAgent(agent.getBaseUrl())
                .executeCommand(String.format("add: %s", GSON.toJson(portMap)));
        logger.info("enablePort result {}", result);

    }

    @Override
    public void disablePort(Port port) {

        Agent agent = agentDao.selectAgentBySsHost(port.getSsHost());

        Map<String, Object> portMap = new HashMap<>();
        portMap.put("server_port", port.getPortNo());

        Result<Map<String, Object>> result = httpFactory.createAgent(agent.getBaseUrl())
                .executeCommand(String.format("remove: %s", GSON.toJson(portMap)));
        logger.info("disablePort result {}", result);

    }

    @Override
    public List<Port> getUsedPortsBySsHost(String ssHost) {
        return portDao.selectUsedPortsBySsHost(ssHost);
    }

    @Override
    public Port getPortByTokenAndPortNo(String token, int portNo) {
        return portDao.selectPortByTokenAndPortNo(token, portNo);
    }

}
