package com.tangyan.zhufengfm.app.model;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-28
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 发现页面上方Tab按钮
 */
public class DiscoverTab implements JSONParsable{

    private String title;

    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            title = json.getString("title");
            contentType = json.getString("contentType");
        }
    }
}
