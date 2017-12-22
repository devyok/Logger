package com.devyok.logger.sample;

import android.app.Application;

import com.devyok.logger.Configuration;
import com.devyok.logger.Logger;
import com.devyok.logger.impl.AndroidConsoleOutputter;
/**
 * @author DengWei
 */
public class SampleApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Configuration config = new Configuration().setDebug(false)
                                                    .setOutputThreadInfo(true)
                                                    .setMethodStackTraceDepth(2)
                                                    .setOutputCodeLine(true)
                                                    .addLogOutputterClass(AndroidConsoleOutputter.class);
        Logger.config(config);

        Configuration customConfiguration = new CustomConfiguration()
                                                    .setOutputPhoneInfos(true) //自定义的输出规则
                                                    .setDebug(false)
                                                    .setOutputThreadInfo(true)
                                                    .setMethodStackTraceDepth(3)
                                                    .setOutputCodeLine(true)
                                                    .setLogFormatterClass(CustomLogFormatter.class)
                                                    .addLogOutputterClass(CustomLogOutputer.class);
        Logger.config(customConfiguration,"MainActivity");
    }
}
