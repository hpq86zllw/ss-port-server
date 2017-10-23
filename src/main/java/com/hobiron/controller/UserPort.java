package com.hobiron.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobiron.bean.Port;
import com.hobiron.bean.Result;
import com.hobiron.exception.NoAvailablePortExcpetion;
import com.hobiron.tx.PortService;
import com.hobiron.utils.Global;

@RestController
@RequestMapping("/user/port")
public class UserPort {

    @Autowired
    private PortService portService;

    private static final Logger logger = LoggerFactory.getLogger(UserPort.class);

    @GetMapping
    public Result<Map<String, Object>> handleGet() {

        String openid = Global.OPENID.get();
        logger.info("openid {}", openid);

        Port port = portService.getPort(openid);
        if (port == null) {
            logger.info("Could not find port by openid {}", openid);
            return Result.newFail("No Port");
        }

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> portDetail = new HashMap<>();
        portDetail.put("usedFlow", port.getFormattedUsedFlow());
        portDetail.put("totalFlow", port.getFormattedTotalFlow());
        portDetail.put("usedFlowPercent", port.getUsedFlowPercent());
        portDetail.put("host", port.getSsHost());
        portDetail.put("portNo", port.getPortNo());
        portDetail.put("password", port.getPassword());
        portDetail.put("encryptMethod", port.getSsEncryptMethod());
        data.put("portDetail", portDetail);

        return Result.newSuccess("Success", data);

    }

    @PostMapping
    public Result<Map<String, Object>> handlePost() {

        try {

            String openid = Global.OPENID.get();
            logger.info("openid {}", openid);
            portService.applyPort(openid);

            return Result.newSuccess("申请成功");

        } catch (NoAvailablePortExcpetion e) {
            logger.error("Fail to apply port", e);
            return Result.newFail("无可用端口");
        }

    }

    @DeleteMapping
    public Result<Map<String, Object>> handleDelete() {

        String openid = Global.OPENID.get();
        logger.info("openid {}", openid);
        portService.deletePort(openid);

        return Result.newSuccess("删除成功");

    }

}
