package com.mumu.zhufengfm.app.model.discoverrec;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class BaseRecommend {
    private String title;
    private boolean hasMore;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            title = json.getString("title");
            hasMore = json.optBoolean("hasMore");
        }
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "BaseRecommend{" +
                "hasMore=" + hasMore +
                ", title='" + title + '\'' +
                '}';
    }
}
