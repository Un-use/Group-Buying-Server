package com.unuse.interceptor;

import com.unuse.auth.api.IAuth;
import com.unuse.auth.service.AuthServerService;
import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.common.SessionData;
import com.unuse.user.api.IUser;
import com.unuse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by unuse on 17/2/9.
 */

public class ServerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthServerService authServerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");

        String url = request.getRequestURI();
        if (url.contains(IAuth.API_PATH_AUTH_USER_CONFIRM) || url.contains(IAuth.API_PATH_AUTH_ADMINISTRATOR_CONFIRM) ||
                url.contains(IUser.API_PATH_USER_REGISTER) || url.contains(IAuth.API_PATH_AUTH_SEND_SMS)) {
            return true;
        }

        String token = request.getParameter("token");
        SessionData sessionData = authServerService.getSessionDataByToken(token);
        if (StringUtil.isEmpty(token)) {
            throw new ResponseException(ResponseResult.RES_INVALID_TOKEN);
        }
        if (!token.equals(sessionData.getToken())) {
            throw new ResponseException(ResponseResult.RES_INVALID_TOKEN);
        }

        request.setAttribute(SessionData.class.getName(), sessionData);

        return true;
    }
}
