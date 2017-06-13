package com.devyok.logger;

/**
 * @author wei.deng
 */
public abstract class AbstractLogOutputter implements LogOutputter{

	protected LogFormatter logFormatter;

	public LogFormatter getLogFormatter() {
		return logFormatter;
	}

	public void setLogFormatter(LogFormatter logFormatter) {
		this.logFormatter = logFormatter;
	}

	@Override
	public int output(int priority, String tag, String msg,Object...args) {
		
		if(logFormatter == null) logFormatter = new DefaultLogFormatter();
		
		synchronized (logFormatter) {
			msg = logFormatter.format(msg,args);
		}
		
		if(onPreOutput(priority,tag,msg)){
			return 0;
		}
		
		if(onOutput(priority,tag,msg)){
			return 1;
		}
		
		return 0;
	}
	/**
	 * 可根据tag拦截是否输出
	 */
	protected boolean onPreOutput(int priority, String tag, String msg) {
		return false;
	}
	
	public abstract boolean onOutput(int priority, String tag, String msg);

}
