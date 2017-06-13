package com.devyok.logger;
/**
 * @author wei.deng
 */
public interface LogOutputter {
	 public int output(int priority, String tag, String msg,Object...args);
}
