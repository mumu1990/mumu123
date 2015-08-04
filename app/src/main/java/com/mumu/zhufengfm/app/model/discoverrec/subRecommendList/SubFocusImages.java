package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SubFocusImages {
    private int id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private int uid;
    private int albumId;
    private boolean share;
    private boolean externalUrl;
    private int subType;
    private int trackId;
    private int specialId;
    private String url;


    public int getSpecialId() {
        return specialId;
    }

    public int getSubType() {
        return subType;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getUrl() {
        return url;
    }

    public int getAlbumId() {
        return albumId;
    }

    public boolean isExternalUrl() {
        return externalUrl;
    }

    public int getId() {
        return id;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getPic() {
        return pic;
    }

    public boolean isShare() {
        return share;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public int getType() {
        return type;
    }

    public int getUid() {
        return uid;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {

                        id = jsonObject.getInt("id");
                        shortTitle = jsonObject.getString("shortTitle");
                        longTitle = jsonObject.getString("longTitle");
                        pic = jsonObject.getString("pic");
                        type = jsonObject.getInt("type");
                        uid = jsonObject.optInt("uid");
                        albumId = jsonObject.optInt("albumId");
                        share = jsonObject.getBoolean("isShare");
                        externalUrl = jsonObject.getBoolean("is_External_url");
                        subType = jsonObject.optInt("subType");
                        trackId = jsonObject.optInt("trackId");
                        specialId = jsonObject.optInt("specialId");
                        url = jsonObject.optString("url");


                    }
                }


    @Override
    public String toString() {
        return "SubFocusImages{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", shortTitle='" + shortTitle + '\'' +
                ", longTitle='" + longTitle + '\'' +
                ", pic='" + pic + '\'' +
                ", type=" + type +
                ", uid=" + uid +
                ", share=" + share +
                ", externalUrl=" + externalUrl +
                ", subType=" + subType +
                ", trackId=" + trackId +
                ", specialId=" + specialId +
                ", url='" + url + '\'' +
                '}';
    }
}
