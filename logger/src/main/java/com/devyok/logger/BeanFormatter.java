package com.devyok.logger;

import java.lang.reflect.Field;

/**
 * Created by wei.deng on 2018/1/3.
 */

class BeanFormatter implements LogFormatter {
    @Override
    public String format(String tag, Object object, Object... args) {
        StringBuffer buffer = new StringBuffer();

        try {
            Field[] fileds = object.getClass().getDeclaredFields();

            if(fileds.length == 0){
                return object.toString();
            }

            for(Field f : fileds){
                f.setAccessible(true);
                String name = f.getName();
                String value = f.get(object).toString();

                buffer.append(name).append(" = ").append(value).append(",");

            }
        } catch(Exception e){
            e.printStackTrace();
        }

        if(buffer.length() > 1){
            buffer = buffer.deleteCharAt(buffer.length() - 1);
        }

        return buffer.toString();
    }



}
