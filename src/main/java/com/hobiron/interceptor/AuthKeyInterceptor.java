package com.hobiron.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.hobiron.bean.Result;
import com.hobiron.bean.WXSession;
import com.hobiron.utils.Global;

public class AuthKeyInterceptor extends HandlerInterceptorAdapter {

    private CacheManager cacheManager;

    private static final Gson GSON = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(AuthKeyInterceptor.class);

    public AuthKeyInterceptor(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.setContentType("application/json; charset=UTF-8");
        String authKey = request.getHeader("Auth-Key");
        logger.info("authKey {}", authKey);
        if (StringUtils.isEmpty(authKey)) {
            response.getWriter().write(GSON.toJson(Result.newError("No Auth-Key")));
            return false;
        }
        WXSession wxSession = cacheManager.getCache("session").get(authKey, WXSession.class);
        logger.info("wxSession {}", wxSession);
        if (wxSession == null) {
            response.getWriter().write(GSON.toJson(Result.newError("Invalid Auth-Key")));
            return false;
        }
        Global.OPENID.set(wxSession.getOpenid());

        return super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        Global.OPENID.remove();

        super.postHandle(request, response, handler, modelAndView);

    }

}
