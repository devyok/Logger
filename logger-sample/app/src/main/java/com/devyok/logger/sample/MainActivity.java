package com.devyok.logger.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.devyok.logger.Logger;

/**
 * @author DengWei
 */
public class MainActivity extends Activity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.global_conifg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.info("testTag","MainActivity LOG");
            }
        });

        this.findViewById(R.id.custom_conifg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Logger.info("MainActivity","MainActivity LOG");
            }
        });

    }
}
