package com.unuse.common;

import java.io.Serializable;

/**
 * Created by Unuse on 2016/10/29.
 */

public class ResponseResult implements Serializable {

	public final static int RES_OK 					= 0;
	public final static int RES_UNKNOW_ERROR 		= 1000;
	public final static int RES_UNSUPPORT 			= 1001;
	public final static int RES_INVALID_PARAM 		= 1002;
	public final static int RES_DAOEXCEPTION 		= 1003;
	public final static int RES_RUNTIME_EXCEPTION 	= 1004;
	public final static int RES_NO_RIGHT 			= 1005;
	public final static int RES_NEED_SIGNIN 		= 1006;
	public final static int RES_AUTH_FAILED 		= 1007;
	public final static int RES_INVALID_TOKEN 		= 1008;
	public final static int RES_NEED_AUTH 			= 1009;
	public final static int RES_TIMEOUT 			= 1010;
	public final static int RES_NOT_ALLOW 			= 1011;
	public final static int RES_NOT_EXIST 			= 1012;

	private int result = RES_OK;
	
	private String errorInfo;

	private String messageInfo;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

	public static ResponseResult retOK() {
		return new ResponseResult();
	}

}
