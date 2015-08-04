package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SubEditorRecommendAlbums {
    private int albumId;
    private String coverLarge;
    private String title;
    private String tags;
    private int tracks;
    private int playsCounts;
    private int isFinished;
    private int trackId;
    private String trackTitle;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            coverLarge = json.getString("coverLarge");
            title = json.getString("title");
            tags = json.getString("tags");
            trackTitle = json.getString("trackTitle");
            albumId = json.getInt("albumId");
            tracks = json.getInt("tracks");
            playsCounts = json.getInt("playsCounts");
            isFinished = json.getInt("isFinished");
            trackId = json.getInt("trackId");
        }
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public int isFinished() {
        return isFinished;
    }

    public int getPlaysCounts() {
        return playsCounts;
    }

    public String getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public int getTrackId() {
        return trackId;
    }

    public int getTracks() {
        return tracks;
    }

    public String getTrackTitle() {
        return trackTitle;
    }


    @Override
    public String toString() {
        return "SubEditorRecommendAlbums{" +
                "albumId=" + albumId +
                ", coverLarge='" + coverLarge + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", tracks=" + tracks +
                ", playsCounts=" + playsCounts +
                ", isFinished=" + isFinished +
                ", trackId=" + trackId +
                ", trackTitle='" + trackTitle + '\'' +
                '}';
    }
}
