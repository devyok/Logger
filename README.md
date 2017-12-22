# Logger
Android  Log输出框架


## 优势 ##

- 规范LOG输出;
- 控制拦截应用中所有LOG输出；
- 可定制LOG输出风格；
- 可定制相关LOG输出时携带的调试信息(方法调用链/线程信息等)；


## 如何使用 ##
[直接看实例代码](https://github.com/devyok/Logger/tree/master/logger-sample)

### 第一步 ###
在gradle中引入Logger

	dependencies {
    	compile 'com.devyok.logger:logger:0.0.9'
	}

### 第二步 ###
在Application#onCreate中配置Logger

	Logger.config(new Configuration().setDebug(false)
                                     .setOutputThreadInfo(true)
                                     .setMethodStackTraceDepth(2)
                                     .setOutputCodeLine(true));

### 第三步 ###
使用Logger提供的接口

	Logger.info(LOG_TAG,"onCreate info enter");
    Logger.debug(LOG_TAG,"onCreate debug enter");
    Logger.error(LOG_TAG,"onCreate error enter");
    Logger.verbose(LOG_TAG,"onCreate verbose enter");
	

## 详细配置 ##

### 1. 方法跟踪 ###
配置如下：
	
	//方法的参数值表示要抓取方法调用的深度
	Logger.config(new Configuration().setMethodStackTraceDepth(2));

以下信息是随着Log一起输出到logcat

	method trace[Instrumentation.callActivityOnCreate:1080->Activity.performCreate:5104->MainActivity.onCreate:19]

**说明：**
“MainActivity.onCreate:19” ， MainActivity.onCreate (类名.方法名)：19 (代码行)， 表示log是在MainActivity#onCreate方法的19行输出的。
-> 表示调用关系，从Activity.performCreate 5104行调用了MainActivity.onCreate,log输出是在onCreate的19行。

### 2. 显示代码行 ###
配置如下：
	
	Logger.config(new Configuration().setOutputCodeLine(true));

以下信息是随着Log一起输出到logcat

	code line[MainActivity.onCreate:19]

**说明：**
“MainActivity.onCreate:19” ， MainActivity.onCreate (类名.方法名)：19 (代码行)， 表示log是在MainActivity#onCreate方法的19行输出的。


### 3. 显示线程信息 ###
配置如下：
	
	Logger.config(new Configuration().setOutputThreadInfo(true));

以下信息是随着Log一起输出到logcat

	thread infos[id = 1,name = main,state = RUNNABLE,isDaemon = false,priority = 5] 


### 4. 开启DEBUG ###
配置如下：
	
	Logger.config(new Configuration().setDebug(true));

开启之后将能够输出所有通过Logger.debug输出的LOG


### 5. 自定义输出 ###
配置如下：
	
	Logger.config(new Configuration().addLogOutputterClass(CustomLogOutputer.class));
	

自定义输出类
	
	CustomLogOutputer.java
	//在输出所有LOG时，添加分割线(**与##)
	public class CustomLogOutputer extends AbstractLogOutputter{

	    @Override
	    public boolean onOutput(int priority, String tag, String msg) {
			
	        Log.i(tag,"******************");
	        System.out.println("msg = " + msg);
	        Log.i(tag,"#################");
	
	        return true;
	    }
	}


### 6. 自定义扩展信息 ###

配置如下：
	
	Logger.config(new Configuration().setLogFormatterClass(CustomLogFormatter.class));
	

自定义扩展
	
	//扩展输出信息
	public class CustomLogFormatter extends DefaultLogFormatter{
	
	    @Override
	    protected String buildExtInofs(Configuration cfg) {
	
	        if(cfg instanceof CustomConfiguration) {
	            CustomConfiguration cuscfg = (CustomConfiguration) cfg;
	            if(cuscfg.isOutputPhoneInfos()){
	                StringBuffer extInfos = new StringBuffer();
	
	                String brand = Build.BRAND;
	                String product = Build.PRODUCT;
	
	                extInfos.append("phone infos[")
	                        .append("brand = ").append(brand).append(",")
	                        .append("product = ").append(product)
	                        .append("]");
	
	                return extInfos.toString();
	            }
	        }
	
	        return "";
	    }
	}

### 7. 针对log tag单独配置 ###

配置如下：
	Logger.config(new Configuration().setLogFormatterClass(CustomLogFormatter.class),"tagName");

经过以上配置之后，tagName针对的log tag将以此配置为准进行输出。


## Proguard ##
如果你使用了proguard来优化工程，你需要添加以下设置

	-keep class com.devyok.logger.** { *; }

## License ##
Logger is released under the [Apache 2.0 license](https://github.com/devyok/Logger/blob/master/LICENSE).

Copyright (C) 2017 DengWei.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.