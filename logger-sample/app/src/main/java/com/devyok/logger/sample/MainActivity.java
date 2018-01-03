package com.devyok.logger.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.devyok.logger.Logger;

import org.json.JSONObject;

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
                Logger.info("testTag","MainActivity info LOG");
            }
        });

        this.findViewById(R.id.custom_conifg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.debug("MainActivity","MainActivity debug LOG");
            }
        });

        this.findViewById(R.id.log_data_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setAddress("bj");
                user.setId("1");
                user.setAge(30);
                user.setName("devyok");

                Logger.debug("MainActivity",user, Logger.DataType.BEAN);

                Logger.debug("MainActivity",user);
            }
        });

        this.findViewById(R.id.log_json_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("age","10");
                    jsonObject.put("name","dw");
                    Logger.info("MainActivity",jsonObject.toString(), Logger.DataType.JSON);
                } catch(Exception e){

                }
            }
        });

        this.findViewById(R.id.log_xml_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.info("MainActivity","<xml></xml>", Logger.DataType.XML);
            }
        });

        this.findViewById(R.id.log_array_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] values = new int[]{1,2,3,4};
                Logger.info("MainActivity",values);
            }
        });

    }
}
