package com.devyok.logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wei.deng on 2018/1/3.
 */
class JsonFormatter implements LogFormatter {

    private static final int JSON_INDENT = 2;

    @Override
    public String format(String tag, Object object, Object... args) {
        String message = String.valueOf(object);
        try {
            message = message.trim();
            if (message.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(message);
                message = jsonObject.toString(JSON_INDENT);
            }
            if (message.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(message);
                message = jsonArray.toString(JSON_INDENT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }
}
