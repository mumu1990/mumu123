package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SubSpecialColumn {
    private int columnType;
    private int specialId;
    private String title;
    private String subtitle;
    private String coverPath;
    private String footnote;
    private String contentType;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            columnType = json.getInt("columnType");
            specialId = json.getInt("specialId");
            title = json.getString("title");
            subtitle = json.getString("subtitle");
            coverPath = json.getString("coverPath");
            footnote = json.getString("footnote");
            contentType = json.getString("contentType");
        }
    }

    public int getColumnType() {
        return columnType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public String getFootnote() {
        return footnote;
    }

    public int getSpecialId() {
        return specialId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "SubSpecialColumn{" +
                "columnType=" + columnType +
                ", specialId=" + specialId +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", footnote='" + footnote + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
