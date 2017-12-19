package com.devyok.logger.sample;

import android.app.Activity;
import android.os.Bundle;

import com.devyok.logger.Logger;
/**
 * @author DengWei
 */
public class MainActivity extends Activity {

    static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.info(LOG_TAG,"onCreate info enter");

        Logger.debug(LOG_TAG,"onCreate debug enter");

    }
}
