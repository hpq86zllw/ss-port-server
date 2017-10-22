package com.hobiron.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobiron.bean.Command;
import com.hobiron.bean.CommandTask;
import com.hobiron.tx.PortService;

@RestController
@RequestMapping("/command/reception")
public class CommandReception {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private PortService portService;

    private static final Logger logger = LoggerFactory.getLogger(CommandReception.class);

    @PostMapping
    public void handlePost(@RequestParam("token") String token, @RequestParam("command") String command) {
        logger.info("token {}, command {}", token, command);
        threadPoolTaskExecutor.execute(new CommandTask(new Command(token, command), portService));
    }

}
