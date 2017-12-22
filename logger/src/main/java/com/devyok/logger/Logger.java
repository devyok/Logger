package com.devyok.logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * log输出服务，Logger是线程安全的,使用参考下面例子：
 * 
 * For example:<p>
 * 
 * <pre class="prettyprint">
 * Logger.config(
 	    new Configuration()
		.setDebug(true)
		.setOutputSystemInfo(false)
		.setOutputThreadInfo(true)
		.setOutputCodeLine(true)
		.setMethodStackTraceDepth(1)
		.setMaxMethodStackTraceDepth(true)
		.addLogOutputterClass(LogOutputter1.class)
		.addLogOutputterClass(LogOutputter2.class)
		.setLogFormatterClass(LogFormatterImpl.class)
	);
	<p>
	<p>Here is an example of subclassing:</p>
	public static class LogFormatterImpl extends DefaultLogFormatter {
		public String format(String message, Object... args) {
			return super.format(message, args);
		}
	}
	<p>
	public static class LogOutputter2 extends AbstractLogOutputter {
		public boolean onOutput(int priority, String tag, String msg) {
			System.out.println("thread name = "+Thread.currentThread().getName()+", LogOutputter2 tag = " + tag + " , msg = " + msg);
			return true;
		}
	}
	</pre>
 * 
 * @author wei.deng
 */
public final class Logger {

	static final ThreadLocal<LogService> sLogServiceHolder = new ThreadLocal<LogService>();
	
	private Logger(){}
	
	private static Configuration DEFAULT = new Configuration();

	private static ConcurrentHashMap<String,Configuration> logtagConfigurations = new ConcurrentHashMap<String, Configuration>();

	static {
		logtagConfigurations.put(LogService.GLOBAL_LOG_TAG, DEFAULT);
	}

	public static void config(Configuration configuration) {
		if(configuration == null){
			return ;
		}
		logtagConfigurations.put(LogService.GLOBAL_LOG_TAG,configuration);
	}

	public static void config(Configuration configuration,String... tags) {

		for(String tag : tags) {
			logtagConfigurations.put(tag,configuration);
		}

	}
	
	public static void verbose(String tag,String message,Object...args){
		getLogService().verbose(tag, message, args);
	}
	
	public static void debug(String tag,String message,Object...args){
		getLogService().debug(tag, message, args);
	}
	
	public static void info(String tag,String message,Object...args){
		getLogService().info(tag, message, args);
	}
	
	public static void error(String tag,String message,Object...args){
		getLogService().error(tag, message, args);
	}
	
	public static void error(String tag,String message,Throwable t,Object...args){
		getLogService().error(tag, message, t, args);
	}
	
	static LogService getLogService() {
		try {
			LogService logService = sLogServiceHolder.get();
			
			if (logService == null) {

				LogService newLogService = new LogService(logtagConfigurations);

				newLogService.build();
				sLogServiceHolder.set(newLogService);

				logService = newLogService;

			}
			return logService;
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoggerRunntimeException("create logservice exception", e);
		}
	}

}
