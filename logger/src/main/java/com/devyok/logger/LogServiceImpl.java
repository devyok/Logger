package com.devyok.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author DengWei
 */
final class LogServiceImpl implements LogService{

	static final int VERBOSE = 2;
	static final int DEBUG = 3;
	static final int INFO = 4;
	static final int WARN = 5;
	static final int ERROR = 6;
	static final int ASSERT = 7;

	static final String GLOBAL_LOG_TAG = "Logger_Global_Tag";

	final Map<String,List<AbstractLogOutputter>> mLogtagOuputers = new HashMap<String, List<AbstractLogOutputter>>();

	private LogFormatter logFormatter = new DefaultLogFormatter();
	
	final Map<String,Configuration> mLogtagConfigs = new HashMap<String, Configuration>();
	
	LogServiceImpl(Map<String,Configuration> logtagConfigs){
		if(logtagConfigs!=null){
			mLogtagConfigs.putAll(logtagConfigs);
		}
	}
	
	public void verbose(String tag,Object message,Object...args){
		output(LogServiceImpl.VERBOSE, tag, message,args);
	}
	
	public void debug(String tag,Object message,Object...args){
		if(isDebug(tag)) {



			output(LogServiceImpl.DEBUG, tag, message,args);
		}
	}

	boolean isDebug(String tag){
		Configuration configuration = mLogtagConfigs.get(tag);
		if(configuration==null){
			configuration = mLogtagConfigs.get(GLOBAL_LOG_TAG);
		}
		return configuration.isDebug();
	}
	
	public void info(String tag,Object message,Object...args){
		output(LogServiceImpl.INFO, tag, message,args);
	}
	
	public void error(String tag,Object message,Object...args){
		output(LogServiceImpl.ERROR, tag, message,args);
	}
	
	public void error(String tag,Object message,Throwable t,Object...args){
		
		message = build(message,t);
		
		output(LogServiceImpl.ERROR, tag, message,args);
	}
	
	private void output(int priority,String tag,Object message,Object...args){
		List<AbstractLogOutputter> outputters = getLogOutputters(tag);
		for(int i = 0;i < outputters.size();i++){
			AbstractLogOutputter item = outputters.get(i);
			item.output(priority, tag, message, args);
		}
	}

	List<AbstractLogOutputter> getLogOutputters(String tag){
		List<AbstractLogOutputter> outputters = mLogtagOuputers.get(tag);
		if(outputters==null){
			return mLogtagOuputers.get(GLOBAL_LOG_TAG);
		}
		return outputters;
	}

	private String build(Object object, Throwable t) {
		String message = String.valueOf(object);
		if (t != null && message != null) {
			message += ": " + LogHelper.getStackTraceString(t);
		} else if (t != null && message == null) {
			message = LogHelper.getStackTraceString(t);
		}
		return message;
	}
	
	public void build(){

		for(Iterator<Map.Entry<String,Configuration>> iter = mLogtagConfigs.entrySet().iterator();iter.hasNext();){

			Map.Entry<String,Configuration> me = iter.next();

			String tag = me.getKey();
			Configuration configuration = me.getValue();

			try {

				Class<? extends LogFormatter> logFormatterClass = configuration.getLogFormatter();
				LogFormatter logFormatter = logFormatterClass.newInstance();

				List<Class<? extends AbstractLogOutputter>> list = configuration.getLogOutputters();
				List<AbstractLogOutputter> outputters = new ArrayList<AbstractLogOutputter>();

				if (list.size() == 0) {
					list.add(getDefaultOutput());
				}

				for (int i = 0; i < list.size(); i++) {
					Class<? extends AbstractLogOutputter> outputterClass = list.get(i);
					AbstractLogOutputter logOutputter = outputterClass.newInstance();
					logOutputter.setLogFormatter(logFormatter);
					outputters.add(logOutputter);
				}

				mLogtagOuputers.put(tag,outputters);


			} catch(Exception e){
				e.printStackTrace();
			}

		}

	}
	
	public Configuration getConfiguration(String tag) {
		if(mLogtagConfigs!=null){
			Configuration config = mLogtagConfigs.get(tag);
			if(config==null){
				return mLogtagConfigs.get(GLOBAL_LOG_TAG);
			}

			return config;
		}
		return null;
	}

	Class<? extends AbstractLogOutputter> getDefaultOutput(){
		try {
			Class<? extends AbstractLogOutputter> defaultImpl = (Class<? extends AbstractLogOutputter>)Class.forName("com.devyok.logger.impl.AndroidConsoleOutputter");
			return defaultImpl;
		} catch(Exception e){
			return null;
		}
	}

	
}
