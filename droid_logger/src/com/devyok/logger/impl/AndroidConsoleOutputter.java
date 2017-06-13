package com.devyok.logger.impl;

import android.util.Log;

import com.devyok.logger.AbstractLogOutputter;
/**
 * @author wei.deng
 */
public class AndroidConsoleOutputter extends AbstractLogOutputter {

	private static final int MIN_STACK_OFFSET = 5;
	
	@Override
	public boolean onOutput(int priority, String tag, String msg) {
		
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		
		
		
		Log.println(priority, tag, msg);
		
		return true;
	}

	private int getStackOffset(StackTraceElement[] trace) {
		for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
			StackTraceElement e = trace[i];
			String name = e.getClassName();
			Log.i("name", "name = " + name);
		}
		return -1;
	}

}
