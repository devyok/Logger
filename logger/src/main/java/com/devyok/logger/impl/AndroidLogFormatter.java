package com.devyok.logger.impl;

import com.devyok.logger.LogFormatter;
/**
 * @author DengWei
 */
public class AndroidLogFormatter implements LogFormatter {

	@Override
	public String format(String tag,String message, Object... args) {
		
		String formatResult = message;
		
		if(args!=null && args.length > 0){
			formatResult = String.format(message, args);
		}
		
		return "result:"+formatResult;
	}

	
}
