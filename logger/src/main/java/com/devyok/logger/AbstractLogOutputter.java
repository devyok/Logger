package com.devyok.logger;

/**
 * @author DengWei
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
	public int output(int priority, String tag, Object msg,Object...args) {

		if(logFormatter == null) logFormatter = new DefaultLogFormatter();
		String result = "";
		synchronized (logFormatter) {

			LogFormatter formatter = LogFormatterFactory.getFormatter(args);
			if(formatter!=null){
				result = formatter.format(tag,msg,args);
			} else {
				result = LogHelper.toString(msg);
			}

			result = logFormatter.format(tag,result,args);
		}

		if(onPreOutput(priority,tag,result)){
			return 0;
		}
		if(onOutput(priority,tag,result)){
			return 1;
		}
		return 0;
	}
	protected boolean onPreOutput(int priority, String tag, String msg) {
		return false;
	}
	
	public abstract boolean onOutput(int priority, String tag, String msg);

}
