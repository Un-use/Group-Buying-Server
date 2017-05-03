package com.unuse.common;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseController.java
 * 
 * Create by Unuse on 2016-10-12
 */

public class BaseController {

	Logger logger = Logger.getLogger(getClass().getName());
	
	@ExceptionHandler
	@ResponseBody
	public ResponseResult resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
		
		ResponseResult res = ret(ex);
		
		logger.error("error exception:",  ex);

		return res;
	}

	private ResponseResult ret(Exception ex) {
		ResponseResult res = new ResponseResult();
		if (ex instanceof ResponseException) {
			ResponseException re = (ResponseException) ex;
			res.setErrorInfo(re.getErrorInfo());
			res.setResult(re.getRc());
		} else {
			res.setErrorInfo("系统内部错误");
			res.setResult(ResponseResult.RES_RUNTIME_EXCEPTION);
		}
		return res;
	}

    public static SessionData getRequestSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = SessionData.class.getName();
        SessionData sessionData = (SessionData) request.getAttribute(name);

        if (null == sessionData) {
            sessionData = new SessionData();
            request.setAttribute(name, sessionData);
        }

        return sessionData;
    }

    public static Long getUid() {
        return getRequestSession().getUid();
    }

    public static String getToken() {
        return getRequestSession().getToken();
    }
	
}
