package com.devyok.logger.sample;

import com.devyok.logger.Configuration;

/**
 * @author DengWei
 */
public class CustomConfiguration extends Configuration{

    private boolean isOutputPhoneInfos;

    public boolean isOutputPhoneInfos(){
        return isOutputPhoneInfos;
    }

    public CustomConfiguration setOutputPhoneInfos(boolean isOutputPhoneInfos){
        this.isOutputPhoneInfos = isOutputPhoneInfos;
        return this;
    }

}
