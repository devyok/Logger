package com.devyok.logger;


/**
 * @author wei.deng
 */
public class DefaultLogFormatter implements LogFormatter{

	final int LOG_DEPTH = 8;
	
	@Override
	public String format(String message, Object... args) {
		
		StringBuffer logBuffer = new StringBuffer();
		
		Configuration configuration = Logger.getLogService().getConfiguration();
		
		String formatResult = message;
		
		if(args!=null && args.length > 0){
			formatResult = String.format(message, args);
		}
		
		logBuffer.append("log[").append(formatResult).append("] ,");
		
		if(configuration.isOutputCodeLine()){
			String codeLine = buildCodeLine();
			logBuffer.append(codeLine);
			logBuffer.append(" ,");
		}
		
		if(configuration.isMaxMethodStackTraceDepth() || configuration.getMethodStackTraceDepth() > 0){
			String methodTrace = buildMethodStackTrace(configuration.isMaxMethodStackTraceDepth() ? Integer.MAX_VALUE : configuration.getMethodStackTraceDepth());
			logBuffer.append(methodTrace);
			logBuffer.append(" ,");
		}
		
		if(configuration.isOutputThreadInfo()){
			String currentThreadInfos = buildThreadInfos();
			logBuffer.append(currentThreadInfos);
			logBuffer.append(" ,");
		}
		
		if(configuration.isOutputSystemInfo()){
			String systemInfos = buildThreadInfos();
			logBuffer.append(systemInfos);
			logBuffer.append(" ,");
		}
		
		logBuffer = logBuffer.deleteCharAt(logBuffer.length() - 1);
		
		
		return logBuffer.toString();
	}

	protected String buildThreadInfos(){
		StringBuffer threadBuffer = new StringBuffer();
		
		Thread curThread = Thread.currentThread();
		long id = curThread.getId();
		String name = curThread.getName();
		String state = curThread.getState().toString();
		boolean isDaemon = curThread.isDaemon();
		int priority = curThread.getPriority();
		
		threadBuffer.append("thread infos[")
					.append("id = ").append(id).append(",")
					.append("name = ").append(name).append(",")
					.append("state = ").append(state).append(",")
					.append("isDaemon = ").append(isDaemon).append(",")
					.append("priority = ").append(priority)
					.append("]");
		
		return threadBuffer.toString();
	}
	
	protected String buildSystemInfos(){
		StringBuffer systemBuffer = new StringBuffer();
		
		systemBuffer.append("system infos[")
		.append("]");
		
		return systemBuffer.toString();
	}
	
	protected String buildMethodStackTrace(int depth){
		StringBuffer methodStackTraceBuffer = new StringBuffer();
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement trace = elements[elements.length - 1];
        methodStackTraceBuffer.append("method trace[");
        int start = elements.length - 1;
        if(depth != Integer.MAX_VALUE) {
        	start = ((LOG_DEPTH+depth) >= elements.length) ? elements.length - 1 : LOG_DEPTH + depth;
        }
        
        for(int i = start;i >= LOG_DEPTH;--i){
        	trace = elements[i];
        	
        	methodStackTraceBuffer.append(getSimpleClassName(trace.getClassName())+"." + trace.getMethodName() + ":" + trace.getLineNumber()).append("->");
        }
        
        methodStackTraceBuffer = methodStackTraceBuffer.delete(methodStackTraceBuffer.length() - 2, methodStackTraceBuffer.length());
        
        methodStackTraceBuffer.append("]");
        
		
		return methodStackTraceBuffer.toString();
	}
	
	protected String buildCodeLine(){
		StringBuffer codeLineBuffer = new StringBuffer();
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        final StackTraceElement trace = elements[LOG_DEPTH];
        
        codeLineBuffer.append("invoke line[")
        			  .append(getSimpleClassName(trace.getClassName())+"." + trace.getMethodName() + ":" + trace.getLineNumber())
        			  .append("]");
        
        
		
		return codeLineBuffer.toString();
	}
	
	private String getSimpleClassName(String name) {
		int lastIndex = name.lastIndexOf(".");
		return name.substring(lastIndex + 1);
	}

}