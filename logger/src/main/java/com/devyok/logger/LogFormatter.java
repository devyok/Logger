package com.devyok.logger;
/**
 * @author wei.deng
 */
public interface LogFormatter {

	String format(String message,Object...args);
	
}
