package com.devyok.logger;
/**
 * @author DengWei
 */
public interface LogFormatter {

	String format(String tag,String message,Object...args);
	
}
