package com.devyok.logger.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.devyok.logger.AbstractLogOutputter;
/**
 * @author wei.deng
 */
public class DiskLogOutputter extends AbstractLogOutputter{

	private ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<String, String>();
	
	@Override
	public boolean onOutput(int priority, String tag, String msg) {
		
		cache.put(tag, msg);
		
		return true;
	}

	

}
