package com.hobiron.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hobiron.bean.Port;
import com.hobiron.bean.Result;
import com.hobiron.bean.Agent;
import com.hobiron.tx.PortService;
import com.hobiron.tx.AgentService;

@RestController
@RequestMapping("/authorization")
public class Authorization {

    @Autowired
    private AgentService agentService;
    @Autowired
    private PortService portService;

    private static final Gson GSON = new Gson();

    private static final Logger logger = LoggerFactory.getLogger(Authorization.class);

    @PostMapping
    public Result<Map<String, Object>> handlePost(@RequestParam("ss_host") String ssHost,
            @RequestParam("ss_min_port") int ssMinPort, @RequestParam("ss_max_port") int ssMaxPort,
            @RequestParam("ss_encrypt_method") String ssEncryptMethod, @RequestParam("base_url") String baseUrl,
            @RequestParam("max_flow_bytes") long maxFlowBytes) {

        logger.info("ssHost {}, ssMinPort {}, ssMaxPort {}, ssEncryptMethod {}, baseUrl {}, maxFlowBytes {}", ssHost,
                ssMinPort, ssMaxPort, ssEncryptMethod, baseUrl, maxFlowBytes);
        Agent agent = new Agent(ssHost, ssMinPort, ssMaxPort, ssEncryptMethod, baseUrl, maxFlowBytes);
        String token = agentService.authorize(agent);
        logger.info("token {}", token);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        List<String> commandList = new ArrayList<>();
        commandList.add("ping");

        List<Port> usedPortList = portService.getUsedPortsBySsHost(agent.getSsHost());
        if (CollectionUtils.isEmpty(usedPortList)) {
            data.put("commands", commandList);
            return Result.newSuccess("Success", data);
        }

        logger.info("Used port list {}", usedPortList);
        for (Port port : usedPortList) {
            Map<String, Object> portMap = new HashMap<>();
            portMap.put("server_port", port.getPortNo());
            portMap.put("password", port.getPassword());
            commandList.add(String.format("add: %s", GSON.toJson(portMap)));
        }

        data.put("commands", commandList);
        return Result.newSuccess("Success", data);

    }

}
