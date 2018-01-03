package com.devyok.logger;


/**
 * @author DengWei
 */
public class DefaultLogFormatter implements LogFormatter{

	final int LOG_DEPTH = 8;
	
	@Override
	public String format(String tag,Object object, Object... args) {
		
		StringBuffer logBuffer = new StringBuffer();
		
		Configuration configuration = Logger.getLogService().getConfiguration(tag);
		String message = String.valueOf(object);
		String formatResult = String.valueOf(message);
		
		if(args!=null && args.length > 0){
			formatResult = String.format(message, args);
		}
		
		logBuffer.append("[").append(formatResult).append("]");
		
		if(configuration.isOutputCodeLine()){
			logBuffer.append("\n");
			String codeLine = buildCodeLine();
			logBuffer.append(codeLine);
		}
		
		if(configuration.isMaxMethodStackTraceDepth() || configuration.getMethodStackTraceDepth() > 0){
			logBuffer.append("\n");
			String methodTrace = buildMethodStackTrace(configuration.isMaxMethodStackTraceDepth() ? Integer.MAX_VALUE : configuration.getMethodStackTraceDepth());
			logBuffer.append(methodTrace);
		}
		
		if(configuration.isOutputThreadInfo()){
			logBuffer.append("\n");
			String currentThreadInfos = buildThreadInfos();
			logBuffer.append(currentThreadInfos);
		}
		
		if(configuration.isOutputSystemInfo()){
			logBuffer.append("\n");
			String systemInfos = buildSystemInfos();
			logBuffer.append(systemInfos);
		}

		String extInfos = buildExtInofs(configuration);

		if(extInfos == null){
//			logBuffer = logBuffer.deleteCharAt(logBuffer.length() - 1);
		} else {
			logBuffer.append(extInfos);
		}

		return logBuffer.toString();
	}

	protected String buildExtInofs(Configuration cfg){

		return null;
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
        
        codeLineBuffer.append("code line[")
        			  .append(getSimpleClassName(trace.getClassName())+"." + trace.getMethodName() + ":" + trace.getLineNumber())
        			  .append("]");
        
        
		
		return codeLineBuffer.toString();
	}
	
	private String getSimpleClassName(String name) {
		int lastIndex = name.lastIndexOf(".");
		return name.substring(lastIndex + 1);
	}

}
