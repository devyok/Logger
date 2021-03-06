package com.devyok.logger.impl;

import com.devyok.logger.AbstractLogOutputter;

import java.util.concurrent.ConcurrentHashMap;
/**
 * @author DengWei
 */
public class DiskLogOutputter extends AbstractLogOutputter{

	private ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<String, String>();
	
	@Override
	public boolean onOutput(int priority, String tag, String msg) {
		
		cache.put(tag, msg);
		
		return true;
	}

	

}
