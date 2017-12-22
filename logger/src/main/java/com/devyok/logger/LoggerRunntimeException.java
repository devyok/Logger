package com.devyok.logger;
/**
 * @author DengWei
 */
public class LoggerRunntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoggerRunntimeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public LoggerRunntimeException(String detailMessage) {
		super(detailMessage);
	}
	

}
