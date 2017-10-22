package com.hobiron.http;

import java.util.Map;

import com.hobiron.bean.Result;

import feign.Body;
import feign.Param;
import feign.RequestLine;

public interface Agent {

    @RequestLine("POST /command/execution")
    @Body("command={command}")
    Result<Map<String, Object>> executeCommand(@Param("command") String command);

}
