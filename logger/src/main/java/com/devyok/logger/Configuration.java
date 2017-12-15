package com.devyok.logger;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private boolean isDebug = false;
	private int methodStackTraceDepth = 0;
	private boolean isMaxMethodStackTraceDepth;
	
	private boolean isOutputThreadInfo = true;
	private boolean isOutputSystemInfo = false;
	private boolean isOutputCodeLine = false;

	private List<Class<? extends AbstractLogOutputter>> logOutputterClasses = new ArrayList<Class<? extends AbstractLogOutputter>>();
	private Class<? extends LogFormatter> logFormatterClass = DefaultLogFormatter.class;
	
	public Configuration(){
	}
	
	public Configuration(Configuration orig) {
		
		this.isDebug = orig.isDebug;
		this.isOutputCodeLine = orig.isOutputCodeLine;
		this.methodStackTraceDepth = orig.methodStackTraceDepth;
		this.isOutputSystemInfo = orig.isOutputSystemInfo;
		this.isOutputThreadInfo =  orig.isOutputThreadInfo;
		this.logOutputterClasses.addAll(orig.logOutputterClasses);
		this.logFormatterClass = orig.logFormatterClass;
		this.isMaxMethodStackTraceDepth = orig.isMaxMethodStackTraceDepth;
		
	}

	public Configuration setOutputSystemInfo(boolean isOutputSystemInfo) {
		this.isOutputSystemInfo = isOutputSystemInfo;
		return this;
	}
	
	public Configuration addLogOutputterClass(Class<? extends AbstractLogOutputter> logOutputterClass) {
		logOutputterClasses.add(logOutputterClass);
		return this;
	}
	
	public Configuration setLogFormatterClass(Class<? extends LogFormatter> logFormatter) {
		this.logFormatterClass = logFormatter;
		return this;
	}
	
	public Configuration setDebug(boolean isDebug) {
		this.isDebug = isDebug;
		return this;
	}
	
	public Configuration setMethodStackTraceDepth(int methodCount) {
		this.methodStackTraceDepth = methodCount;
		return this;
	}
	public boolean isOutputThreadInfo() {
		return isOutputThreadInfo;
	}
	
	public Configuration setOutputThreadInfo(boolean isOutputThreadInfo) {
		this.isOutputThreadInfo = isOutputThreadInfo;
		return this;
	} 

	public boolean isOutputSystemInfo() {
		return isOutputSystemInfo;
	}
	
	public List<Class<? extends AbstractLogOutputter>> getLogOutputters() {
		return logOutputterClasses;
	}
	
	public Class<? extends LogFormatter> getLogFormatter() {
		return logFormatterClass;
	}
	
	public int getMethodStackTraceDepth() {
		return methodStackTraceDepth;
	}
	
	public boolean isDebug() {
		return isDebug;
	}
	
	public boolean isOutputCodeLine() {
		return isOutputCodeLine;
	}

	public Configuration setOutputCodeLine(boolean isOutputCodeLine) {
		this.isOutputCodeLine = isOutputCodeLine;
		return this;
	}
	public boolean isMaxMethodStackTraceDepth() {
		return isMaxMethodStackTraceDepth;
	}

	public Configuration setMaxMethodStackTraceDepth(boolean isMaxMethodStackTraceDepth) {
		this.isMaxMethodStackTraceDepth = isMaxMethodStackTraceDepth;
		return this;
	}
}
