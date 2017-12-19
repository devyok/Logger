# Logger
Android  Log输出框架


## 优势 ##

- 规范LOG输出;

- 统一拦截所有LOG输出；

- 可定制LOG输出格式与调试信息；


## 如何使用 ##
[直接看实例代码](https://github.com/devyok/Logger/tree/master/logger-sample)

### 第一步 ###
在gradle中引入Logger

	dependencies {
    	compile 'com.devyok.logger:logger:0.0.8'
	}

### 第二步 ###
在Application#onCreate中配置Logger

	Logger.config(new Configuration().setDebug(false)
                                     .setOutputThreadInfo(true)
                                     .setMethodStackTraceDepth(2)
                                     .setOutputCodeLine(true));

### 第三步 ###
在Application#onCreate中配置Logger

	Logger.info(LOG_TAG,"onCreate info enter");
    Logger.debug(LOG_TAG,"onCreate debug enter");
    Logger.error(LOG_TAG,"onCreate error enter");
    Logger.verbose(LOG_TAG,"onCreate verbose enter");
	

经过以上步骤就可以实现控制。



## 详细配置 ##

### 方法跟踪 ###
配置如下：
	
	Logger.config(new Configuration().setMethodStackTraceDepth(2));

以下信息是随着Log一起输出到logcat

	method trace[Instrumentation.callActivityOnCreate:1080->Activity.performCreate:5104->MainActivity.onCreate:19]

**说明：**
“MainActivity.onCreate:19” ， MainActivity.onCreate (类名.方法名)：19 (代码行)， 表示log是在MainActivity#onCreate方法的19行输出的。
-> 表示调用关系，从Activity.performCreate 5104行调用了MainActivity.onCreate,log输出是在onCreate的19行。

### 显示代码行 ###
配置如下：
	
	Logger.config(new Configuration().setOutputCodeLine(true));

以下信息是随着Log一起输出到logcat

	code line[MainActivity.onCreate:19]

**说明：**
“MainActivity.onCreate:19” ， MainActivity.onCreate (类名.方法名)：19 (代码行)， 表示log是在MainActivity#onCreate方法的19行输出的。


### 显示线程信息 ###
配置如下：
	
	Logger.config(new Configuration().setDebug(false)
                                     .setOutputThreadInfo(true)

以下信息是随着Log一起输出到logcat

	thread infos[id = 1,name = main,state = RUNNABLE,isDaemon = false,priority = 5] 

**说明：**
输出当前线程信息(id，名称，状态，优先级)



