package com.devyok.logger.sample;

import android.util.Log;

import com.devyok.logger.AbstractLogOutputter;

/**
 * @author DengWei
 */
public class CustomLogOutputer extends AbstractLogOutputter{

    @Override
    public boolean onOutput(int priority, String tag, String msg) {

        Log.i(tag,"******************");
        if(tag.contains("Activity")) {
            Log.i(tag,"UI LOG");
        }

        Log.i(tag,String.valueOf(msg));

        Log.i(tag,"#################");

        return false;
    }
}
