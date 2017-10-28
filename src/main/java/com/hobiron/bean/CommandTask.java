package com.hobiron.bean;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hobiron.exception.NoAvailablePortExcpetion;
import com.hobiron.tx.PortService;

public class CommandTask implements Runnable {

    private Command command;
    private PortService portService;

    private static final Logger logger = LoggerFactory.getLogger(CommandTask.class);

    public CommandTask(Command command, PortService portService) {
        this.command = command;
        this.portService = portService;
    }

    @Override
    public void run() {

        logger.info("command:{}", command);
        if ("ok".equals(command)) {
            logger.info("Command execute successfully");
            return;
        }
        AgentFlow agentFlow = command.convertToAgentFlow();

        Map<Integer, Long> portBytesMap = agentFlow.getPortBytesMap();
        logger.info("portBytesMap:{}", portBytesMap);
        Set<Integer> portBytesMapKeySet = portBytesMap.keySet();
        for (Integer portBytesMapKey : portBytesMapKeySet) {

            PortFlow portFlow = new PortFlow(agentFlow.getToken(), portBytesMapKey, portBytesMap.get(portBytesMapKey));

            try {
                logger.info(String.format("portFlow:%s", portFlow));
                portService.calculatePortFlow(portFlow);
            } catch (NoAvailablePortExcpetion e) {

                logger.error("Fail to calculate port", e);
                Port port = portService.getPortByTokenAndPortNo(portFlow.getToken(), portFlow.getPortNo());
                portService.disablePort(port);

            }

        }

    }

}
