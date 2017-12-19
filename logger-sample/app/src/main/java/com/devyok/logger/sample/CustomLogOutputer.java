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
        System.out.println("priority = " + priority);
        System.out.println("tag = " + tag);
        System.out.println("msg = " + msg);
        Log.i(tag,"#################");

        return false;
    }
}
