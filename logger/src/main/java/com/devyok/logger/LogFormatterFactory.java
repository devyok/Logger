package com.devyok.logger;

/**
 * Created by wei.deng on 2018/1/3.
 */

class LogFormatterFactory {

    static LogFormatter getFormatter(Object... args) {
        int len = args.length;
        if(len > 0){
            Object obj = args[len - 1];

            if(obj != null || obj instanceof Logger.DataType){

                Logger.DataType dataType = (Logger.DataType) obj;

                return getFormatter(dataType);

            }

        }

        return null;
    }

    static LogFormatter getFormatter(Logger.DataType dataType) {

        if(dataType == Logger.DataType.JSON) {
            return new JsonFormatter();
        } else if(dataType == Logger.DataType.XML) {
            return new XmlFormatter();
        } else if(dataType == Logger.DataType.BEAN) {
            return new BeanFormatter();
        }

        return null;
    }

}
