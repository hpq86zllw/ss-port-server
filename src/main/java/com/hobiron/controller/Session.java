package com.hobiron.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobiron.bean.Result;
import com.hobiron.bean.WXSession;
import com.hobiron.conf.WXConfig;
import com.hobiron.http.WX;
import com.hobiron.tx.UserService;

@RestController
@RequestMapping("/session")
public class Session {

    @Autowired
    private WXConfig wxConfig;
    @Autowired
    private WX wx;
    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    private static final Logger logger = LoggerFactory.getLogger(Session.class);

    @PostMapping
    public Result<Map<String, Object>> handlePost(@RequestParam("code") String code,
            @RequestParam("nickname") String nickname) {

        logger.info("code {}, nickname {}", code, nickname);
        try {

            WXSession wxSession = wx.jscode2session(wxConfig.getAppid(), wxConfig.getAppsecret(), code);
            logger.info("wxSession {}", wxSession);

            userService.save(wxSession.getOpenid(), nickname);

            Map<String, Object> data = new HashMap<String, Object>();
            String authKey = UUID.randomUUID().toString();
            data.put("authKey", authKey);
            cacheManager.getCache("session").put(authKey, wxSession);

            return Result.newSuccess("Success", data);

        } catch (Exception e) {
            logger.error("Fail to get session key", e);
            return Result.newFail("Fail to login");
        }

    }

}
