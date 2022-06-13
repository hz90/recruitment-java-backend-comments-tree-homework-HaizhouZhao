package com.test.pro.vo;

public class ResponseVo<T> {
	ErrorInfo errorInfo;
	/**
	 * 具体内容
	 **/
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

}
