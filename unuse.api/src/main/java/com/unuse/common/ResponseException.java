package com.unuse.common;

/**
 * Create by Unuse on 2016-10-12
 */

public class ResponseException extends RuntimeException {

	private int rc;
	
	private String errorInfo;

	public ResponseException() {
	}

	public ResponseException(int rc, String errorInfo) {
		this.rc = rc;
		this.errorInfo = errorInfo;
	}

	public ResponseException(int rc) {
		this.rc = rc;
	}

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
