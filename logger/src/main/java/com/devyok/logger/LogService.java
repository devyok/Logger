package com.devyok.logger;

/**
 * Created by wei.deng on 2018/1/3.
 */

public interface LogService {

    public void verbose(String tag,Object message,Object...args);

    public void debug(String tag,Object message,Object...args);

    public void info(String tag,Object message,Object...args);

    public void error(String tag,Object message,Object...args);

    public Configuration getConfiguration(String tag);

}
