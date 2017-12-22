package com.devyok.logger;
/**
 * @author DengWei
 */
public interface LogOutputter {
	 public int output(int priority, String tag, String msg,Object...args);
}
