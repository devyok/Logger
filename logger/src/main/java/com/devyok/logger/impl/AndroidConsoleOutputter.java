package com.devyok.logger.impl;

import android.util.Log;

import com.devyok.logger.AbstractLogOutputter;
/**
 * @author wei.deng
 */
public class AndroidConsoleOutputter extends AbstractLogOutputter {

	@Override
	public boolean onOutput(int priority, String tag, String msg) {

		if(priority == Log.DEBUG) {
			Log.println(Log.INFO,tag,msg);
		} else {
			Log.println(priority, tag, msg);
		}

		return true;
	}


}
