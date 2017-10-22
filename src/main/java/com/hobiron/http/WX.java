package com.hobiron.http;

import com.hobiron.bean.WXSession;

import feign.Param;
import feign.RequestLine;

public interface WX {

    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code")
    WXSession jscode2session(@Param("appid") String appid, @Param("secret") String secret,
            @Param("js_code") String jsCode);

}
