package com.devyok.logger.sample;

import android.os.Build;

import com.devyok.logger.Configuration;
import com.devyok.logger.DefaultLogFormatter;

/**
 * @author DengWei
 */
public class CustomLogFormatter extends DefaultLogFormatter{

    @Override
    protected String buildSystemInfos() {
        return super.buildSystemInfos();
    }

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
