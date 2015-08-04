package com.mumu.zhufengfm.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-28
 */
public interface JSONParsable {
    void parseJSON(JSONObject json) throws JSONException;
}
