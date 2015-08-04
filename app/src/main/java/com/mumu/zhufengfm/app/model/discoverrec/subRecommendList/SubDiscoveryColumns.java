package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SubDiscoveryColumns {
    private int id;
    private int orderNum;
    private int contentUpdatedAt;
    private boolean enableShare;
    private String title;
    private String subtitle;
    private String coverPath;
    private String contentType;
    private String url;
    private String sharePic;
    private boolean hot;
    private boolean externalUrl;



    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            id = json.getInt("id");
            orderNum = json.getInt("orderNum");
            contentUpdatedAt = json.getInt("contentUpdatedAt");
            enableShare = json.getBoolean("enableShare");
            title = json.getString("title");
            subtitle = json.getString("subtitle");
            coverPath = json.getString("coverPath");
            contentType = json.getString("contentType");
            url = json.optString("url");
            sharePic = json.optString("sharePic");
            hot=json.optBoolean("isHot");
            externalUrl=json.optBoolean("isExternalUrl");

        }
    }

    public boolean externalUrl() {
        return externalUrl;
    }

    public boolean isHot() {
        return hot;
    }

    public String getContentType() {
        return contentType;
    }

    public int getContentUpdatedAt() {
        return contentUpdatedAt;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isEnableShare() {
        return enableShare;
    }

    public int getId() {
        return id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getSharePic() {
        return sharePic;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "SubDiscoveryColumns{" +
                "contentType='" + contentType + '\'' +
                ", id=" + id +
                ", orderNum=" + orderNum +
                ", contentUpdatedAt=" + contentUpdatedAt +
                ", enableShare=" + enableShare +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", url='" + url + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", hot=" + hot +
                ", externalUrl=" + externalUrl +
                '}';
    }
}
