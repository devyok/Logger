package com.devyok.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DengWei
 */
final class LogService {

	static final int VERBOSE = 2;
	static final int DEBUG = 3;
	static final int INFO = 4;
	static final int WARN = 5;
	static final int ERROR = 6;
	static final int ASSERT = 7;
	
	private List<AbstractLogOutputter> logOutputters = new ArrayList<AbstractLogOutputter>();
	private LogFormatter logFormatter = new DefaultLogFormatter();
	
	private Configuration mConfiguration;
	
	LogService(Configuration configuration){
		mConfiguration = configuration;
	}
	
	public void verbose(String tag,String message,Object...args){
		output(LogService.VERBOSE, tag, message,args);
	}
	
	public void debug(String tag,String message,Object...args){
		if(mConfiguration.isDebug()) {
			output(LogService.DEBUG, tag, message,args);
		}
	}
	
	public void info(String tag,String message,Object...args){
		output(LogService.INFO, tag, message,args);
	}
	
	public void error(String tag,String message,Object...args){
		output(LogService.ERROR, tag, message,args);
	}
	
	public void error(String tag,String message,Throwable t,Object...args){
		
		message = build(message,t);
		
		output(LogService.ERROR, tag, message,args);
	}
	
	private void output(int priority,String tag,String message,Object...args){
		List<AbstractLogOutputter> outputters = getLogOutputters();
		for(int i = 0;i < outputters.size();i++){
			AbstractLogOutputter item = outputters.get(i);
			item.output(priority, tag, message, args);
		}
	}
	
	private String build(String message, Throwable t) {
		if (t != null && message != null) {
			message += ": " + LogHelper.getStackTraceString(t);
		} else if (t != null && message == null) {
			message = LogHelper.getStackTraceString(t);
		}
		return message;
	}
	
	public void addLogOutputter(AbstractLogOutputter logOutputter) {
		logOutputters.add(logOutputter);
	}
	
	public void setLogFormatter(LogFormatter logFormatter) {
		this.logFormatter = logFormatter;
	}
	
	public List<AbstractLogOutputter> getLogOutputters() {
		return logOutputters;
	}
	
	public LogFormatter getLogFormatter() {
		return logFormatter;
	}
	
	public void build(){
		for(int i = 0;i < logOutputters.size();i++){
			logOutputters.get(i).setLogFormatter(logFormatter);
		}
	}
	
	public Configuration getConfiguration() {
		return mConfiguration;
	}
	
}
