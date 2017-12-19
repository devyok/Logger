package com.devyok.logger;

import java.util.List;

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
	
	private static Configuration sConfiguration = new Configuration();
	
	public static void config(Configuration configuration) {
		if(configuration == null){
			return ;
		}
		sConfiguration = configuration;
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

				Configuration configuration = sConfiguration;

				LogService newLogService = new LogService(configuration);

				Class<? extends LogFormatter> logFormatterClass = configuration.getLogFormatter();
				LogFormatter logFormatter = logFormatterClass.newInstance();
				newLogService.setLogFormatter(logFormatter);
				
				List<Class<? extends AbstractLogOutputter>> list = configuration.getLogOutputters();
				if (list.size() == 0) {
					list.add(getDefaultOutput());
				}
				for (int i = 0; i < list.size(); i++) {
					Class<? extends AbstractLogOutputter> outputterClass = list.get(i);
					AbstractLogOutputter logOutputter = outputterClass.newInstance();
					newLogService.addLogOutputter(logOutputter);
				}
				
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

	static Class<? extends AbstractLogOutputter> getDefaultOutput(){
		try {
			Class<? extends AbstractLogOutputter> defaultImpl = (Class<? extends AbstractLogOutputter>)Class.forName("com.devyok.logger.impl.AndroidConsoleOutputter");
			return defaultImpl;
		} catch(Exception e){
			return null;
		}
	}

}
